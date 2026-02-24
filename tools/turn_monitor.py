#!/usr/bin/env python3
"""
Nova2 TURN Server Monitor

监控 TURN 服务器的存活状态、凭据有效性和活跃连接。

功能:
1. STUN Binding 探活 — 检测端口是否响应
2. TURN Allocate 认证 — 验证凭据是否仍然有效
3. 中继地址端口范围分析 — 通过多次 Allocate 观察端口分配模式推测并发量
4. 定时巡检 + 日志记录

用法:
    python3 turn_monitor.py                # 单次探测
    python3 turn_monitor.py --watch 300    # 每 5 分钟巡检
    python3 turn_monitor.py --log turn.log # 结果写入日志文件
"""

import socket
import struct
import os
import hashlib
import hmac
import time
import json
import argparse
import sys
from datetime import datetime, timezone


# === 已知 TURN 服务器 (从 APK 逆向提取) ===
TURN_SERVERS = [
    {
        "host": "101.36.120.3",
        "port": 3478,
        "username": "wumitech",
        "password": "wumitech.com@123",
        "label": "TURN #1 (HK)",
    },
    {
        "host": "106.75.153.105",
        "port": 3478,
        "username": "wumitech",
        "password": "wumitech.com@123",
        "label": "TURN #2 (Shanghai)",
    },
]

MAGIC_COOKIE = 0x2112A442


def build_stun_binding_request():
    """构造 STUN Binding Request (RFC 5389)"""
    txn_id = os.urandom(12)
    return struct.pack("!HHI", 0x0001, 0, MAGIC_COOKIE) + txn_id, txn_id


def parse_xor_address(attr_val, txn_id):
    """解析 XOR-MAPPED-ADDRESS / XOR-RELAYED-ADDRESS"""
    family = attr_val[1]
    xport = struct.unpack("!H", attr_val[2:4])[0] ^ 0x2112
    xip_raw = struct.unpack("!I", attr_val[4:8])[0] ^ MAGIC_COOKIE
    ip_str = socket.inet_ntoa(struct.pack("!I", xip_raw))
    return ip_str, xport


def parse_attributes(data, offset, length):
    """解析 STUN 属性列表"""
    attrs = {}
    pos = offset
    end = offset + length
    while pos < end:
        if pos + 4 > end:
            break
        attr_type = struct.unpack("!H", data[pos : pos + 2])[0]
        attr_len = struct.unpack("!H", data[pos + 2 : pos + 4])[0]
        attr_val = data[pos + 4 : pos + 4 + attr_len]
        attrs[attr_type] = attr_val
        pos += 4 + attr_len + (4 - attr_len % 4) % 4
    return attrs


def stun_probe(host, port, timeout=5):
    """STUN Binding 探活"""
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.settimeout(timeout)
    try:
        req, txn_id = build_stun_binding_request()
        t0 = time.time()
        sock.sendto(req, (host, port))
        data, addr = sock.recvfrom(1024)
        rtt_ms = (time.time() - t0) * 1000

        resp_type = struct.unpack("!H", data[:2])[0]
        resp_len = struct.unpack("!H", data[2:4])[0]

        result = {
            "alive": True,
            "rtt_ms": round(rtt_ms, 1),
            "resp_type": f"0x{resp_type:04x}",
        }

        if resp_type == 0x0101:  # Binding Success
            attrs = parse_attributes(data, 20, resp_len)
            # XOR-MAPPED-ADDRESS (0x0020)
            if 0x0020 in attrs:
                ip, port_mapped = parse_xor_address(attrs[0x0020], txn_id)
                result["mapped_address"] = f"{ip}:{port_mapped}"
            # SOFTWARE (0x8022)
            if 0x8022 in attrs:
                result["software"] = attrs[0x8022].decode(errors="replace").strip("\x00")

        return result
    except socket.timeout:
        return {"alive": False, "error": "timeout"}
    except Exception as e:
        return {"alive": False, "error": str(e)}
    finally:
        sock.close()


def turn_allocate(host, port, username, password, timeout=5):
    """完整 TURN Allocate 流程 (两步认证)"""
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.settimeout(timeout)
    try:
        # Step 1: Allocate without auth → 获取 realm + nonce
        txn_id1 = os.urandom(12)
        req1 = struct.pack("!HHI", 0x0003, 0, MAGIC_COOKIE) + txn_id1
        sock.sendto(req1, (host, port))
        data1, _ = sock.recvfrom(2048)

        resp_type1 = struct.unpack("!H", data1[:2])[0]
        resp_len1 = struct.unpack("!H", data1[2:4])[0]

        if resp_type1 != 0x0113:  # Not Allocate Error
            return {"auth_ok": False, "error": f"unexpected_response_0x{resp_type1:04x}"}

        attrs1 = parse_attributes(data1, 20, resp_len1)
        realm = attrs1.get(0x0014, b"").decode()
        nonce = attrs1.get(0x0015, b"").decode()

        if not realm or not nonce:
            return {"auth_ok": False, "error": "no_realm_or_nonce"}

        # Step 2: Allocate with credentials
        key = hashlib.md5(f"{username}:{realm}:{password}".encode()).digest()
        txn_id2 = os.urandom(12)

        attrs_buf = b""

        # USERNAME
        uname_b = username.encode()
        pad = (4 - len(uname_b) % 4) % 4
        attrs_buf += struct.pack("!HH", 0x0006, len(uname_b)) + uname_b + b"\x00" * pad

        # REALM
        realm_b = realm.encode()
        pad = (4 - len(realm_b) % 4) % 4
        attrs_buf += struct.pack("!HH", 0x0014, len(realm_b)) + realm_b + b"\x00" * pad

        # NONCE
        nonce_b = nonce.encode()
        pad = (4 - len(nonce_b) % 4) % 4
        attrs_buf += struct.pack("!HH", 0x0015, len(nonce_b)) + nonce_b + b"\x00" * pad

        # REQUESTED-TRANSPORT (UDP=17)
        attrs_buf += struct.pack("!HH", 0x0019, 4) + struct.pack("!BBH", 17, 0, 0)

        # MESSAGE-INTEGRITY
        msg_for_mi = (
            struct.pack("!HHI", 0x0003, len(attrs_buf) + 24, MAGIC_COOKIE)
            + txn_id2
            + attrs_buf
        )
        mi = hmac.new(key, msg_for_mi, hashlib.sha1).digest()
        attrs_buf += struct.pack("!HH", 0x0008, 20) + mi

        req2 = struct.pack("!HHI", 0x0003, len(attrs_buf), MAGIC_COOKIE) + txn_id2 + attrs_buf
        t0 = time.time()
        sock.sendto(req2, (host, port))
        data2, _ = sock.recvfrom(2048)
        rtt_ms = (time.time() - t0) * 1000

        resp_type2 = struct.unpack("!H", data2[:2])[0]
        resp_len2 = struct.unpack("!H", data2[2:4])[0]

        result = {
            "realm": realm,
            "rtt_ms": round(rtt_ms, 1),
        }

        if resp_type2 == 0x0103:  # Allocate Success
            result["auth_ok"] = True
            attrs2 = parse_attributes(data2, 20, resp_len2)

            if 0x0016 in attrs2:  # XOR-RELAYED-ADDRESS
                ip, rport = parse_xor_address(attrs2[0x0016], txn_id2)
                result["relay_address"] = f"{ip}:{rport}"
                result["relay_port"] = rport

            if 0x0020 in attrs2:  # XOR-MAPPED-ADDRESS
                ip, mport = parse_xor_address(attrs2[0x0020], txn_id2)
                result["mapped_address"] = f"{ip}:{mport}"

            if 0x000D in attrs2:  # LIFETIME
                lifetime = struct.unpack("!I", attrs2[0x000D][:4])[0]
                result["lifetime_sec"] = lifetime

        elif resp_type2 == 0x0113:  # Allocate Error
            result["auth_ok"] = False
            attrs2 = parse_attributes(data2, 20, resp_len2)
            if 0x0009 in attrs2:
                ev = attrs2[0x0009]
                result["error_code"] = f"{ev[2]}{ev[3]:02d}"
        else:
            result["auth_ok"] = False
            result["error"] = f"unexpected_0x{resp_type2:04x}"

        return result
    except socket.timeout:
        return {"auth_ok": False, "error": "timeout"}
    except Exception as e:
        return {"auth_ok": False, "error": str(e)}
    finally:
        sock.close()


def port_range_analysis(host, port, username, password, samples=5):
    """
    多次 Allocate 采样中继端口，分析端口分配模式。
    端口跳跃幅度可间接推测服务器并发 allocation 数量。
    """
    ports = []
    for _ in range(samples):
        result = turn_allocate(host, port, username, password)
        if result.get("auth_ok") and "relay_port" in result:
            ports.append(result["relay_port"])
        time.sleep(0.3)

    if len(ports) < 2:
        return {"samples": len(ports), "ports": ports}

    ports.sort()
    gaps = [ports[i + 1] - ports[i] for i in range(len(ports) - 1)]
    return {
        "samples": len(ports),
        "ports": ports,
        "port_range": f"{min(ports)}-{max(ports)}",
        "avg_gap": round(sum(gaps) / len(gaps), 1),
        "min_gap": min(gaps),
        "max_gap": max(gaps),
    }


def probe_all(verbose=True):
    """探测所有 TURN 服务器"""
    timestamp = datetime.now(timezone.utc).strftime("%Y-%m-%d %H:%M:%S UTC")
    results = {"timestamp": timestamp, "servers": []}

    for srv in TURN_SERVERS:
        host, port = srv["host"], srv["port"]
        label = srv["label"]

        if verbose:
            print(f"\n{'='*60}")
            print(f"  {label}  ({host}:{port})")
            print(f"{'='*60}")

        # 1. STUN 探活
        stun = stun_probe(host, port)
        if verbose:
            if stun["alive"]:
                print(f"  [STUN]  存活 ✓  RTT={stun['rtt_ms']}ms", end="")
                if "software" in stun:
                    print(f"  Software: {stun['software']}", end="")
                if "mapped_address" in stun:
                    print(f"  Mapped: {stun['mapped_address']}", end="")
                print()
            else:
                print(f"  [STUN]  离线 ✗  ({stun.get('error', 'unknown')})")

        # 2. TURN Allocate 认证
        alloc = {"auth_ok": False, "error": "stun_offline"}
        port_analysis = {}
        if stun["alive"]:
            alloc = turn_allocate(host, port, srv["username"], srv["password"])
            if verbose:
                if alloc.get("auth_ok"):
                    print(f"  [AUTH]  凭据有效 ✓  Realm={alloc['realm']}")
                    print(f"          中继地址: {alloc.get('relay_address', 'N/A')}")
                    print(f"          Lifetime: {alloc.get('lifetime_sec', 'N/A')}s")
                else:
                    err = alloc.get("error_code", alloc.get("error", "unknown"))
                    print(f"  [AUTH]  认证失败 ✗  ({err})")

            # 3. 端口分析
            if alloc.get("auth_ok"):
                port_analysis = port_range_analysis(host, port, srv["username"], srv["password"])
                if verbose and port_analysis.get("samples", 0) >= 2:
                    print(f"  [PORT]  采样 {port_analysis['samples']} 次")
                    print(f"          端口范围: {port_analysis['port_range']}")
                    print(f"          平均间隔: {port_analysis['avg_gap']}")
                    print(f"          端口列表: {port_analysis['ports']}")

        server_result = {
            "label": label,
            "host": host,
            "port": port,
            "stun": stun,
            "allocate": alloc,
            "port_analysis": port_analysis,
        }
        results["servers"].append(server_result)

    return results


def main():
    parser = argparse.ArgumentParser(description="Nova2 TURN Server Monitor")
    parser.add_argument(
        "--watch",
        type=int,
        metavar="SECONDS",
        help="定时巡检间隔（秒）",
    )
    parser.add_argument("--log", metavar="FILE", help="日志输出文件 (JSON Lines)")
    parser.add_argument("--json", action="store_true", help="输出 JSON 格式")
    parser.add_argument("--quiet", action="store_true", help="静默模式")
    args = parser.parse_args()

    log_file = None
    if args.log:
        log_file = open(args.log, "a")

    try:
        while True:
            results = probe_all(verbose=not args.quiet and not args.json)

            if args.json:
                print(json.dumps(results, ensure_ascii=False))

            if log_file:
                log_file.write(json.dumps(results, ensure_ascii=False) + "\n")
                log_file.flush()

            if not args.quiet and not args.json:
                # 打印摘要
                print(f"\n{'─'*60}")
                alive = sum(1 for s in results["servers"] if s["stun"]["alive"])
                auth = sum(1 for s in results["servers"] if s["allocate"].get("auth_ok"))
                print(f"  摘要: {alive}/{len(results['servers'])} 存活, {auth}/{len(results['servers'])} 凭据有效")
                print(f"  时间: {results['timestamp']}")

            if not args.watch:
                break

            if not args.quiet:
                print(f"\n  下次检测: {args.watch}s 后...")
            time.sleep(args.watch)

    except KeyboardInterrupt:
        if not args.quiet:
            print("\n\n监控已停止。")
    finally:
        if log_file:
            log_file.close()


if __name__ == "__main__":
    main()
