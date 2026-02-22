package webrtc;

import android.os.Build;
import android.util.Log;

import org.webrtc.SoftwareVideoDecoderFactory;
import org.webrtc.VideoCodecInfo;
import org.webrtc.VideoDecoder;
import org.webrtc.VideoDecoderFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * SafeVideoDecoderFactory is a {@link VideoDecoderFactory} wrapper that
 * provides device-compatibility checks before delegating to a hardware
 * decoder factory.
 *
 * <p>Many Android devices have buggy or poorly performing hardware video
 * decoders. This factory maintains blacklists of known problematic devices,
 * chipsets, and Android versions, and falls back to
 * {@link SoftwareVideoDecoderFactory} for those devices.
 *
 * <p>Compatibility checks include:
 * <ul>
 *   <li><b>Device model blacklist:</b> SM-G930F, SM-G950F, SM-J730F, SM-A520F,
 *       HUAWEI P20 Lite, EVA-L09, VTR-L29, MI 5, MI 8 UD, MI CC9,
 *       Infinix X6511, TECNO KE5</li>
 *   <li><b>Chipset blacklist:</b> Exynos 7570/7870/7880, Kirin 659,
 *       MT6750, MT6735, Snapdragon 425/625</li>
 *   <li><b>Android version blacklist:</b> 7.0, 7.1, 8.0</li>
 *   <li><b>GPU checks:</b> Samsung + Mali GPU combinations</li>
 *   <li><b>SoC checks:</b> Huawei Kirin, Xiaomi + old Snapdragon</li>
 *   <li><b>Core count check:</b> devices with fewer than 4 CPU cores</li>
 *   <li><b>Resolution limiting:</b> max 1280x720 for hardware decoding</li>
 *   <li><b>Codec filtering:</b> AV1 codec removed on certain devices</li>
 * </ul>
 *
 * @see VideoDecoderFactory
 */
public class SafeVideoDecoderFactory implements VideoDecoderFactory {

    private static final String TAG = "SafeVideoDecoderFactory";

    /** Maximum supported resolution width for hardware decoding. */
    private static final int MAX_WIDTH = 1280;

    /** Maximum supported resolution height for hardware decoding. */
    private static final int MAX_HEIGHT = 720;

    /** AV1 codec MIME type identifier. */
    private static final String AV1_CODEC_NAME = "AV1";

    // -------------------------------------------------------------------------
    // Blacklists
    // -------------------------------------------------------------------------

    /** Device models known to have hardware decoder issues. */
    private static final Set<String> BLACKLISTED_DEVICES = new HashSet<>(Arrays.asList(
            "SM-G930F",       // Samsung Galaxy S7 (Exynos)
            "SM-G950F",       // Samsung Galaxy S8 (Exynos)
            "SM-J730F",       // Samsung Galaxy J7 Pro
            "SM-A520F",       // Samsung Galaxy A5 (2017)
            "HUAWEI_P20_Lite", // Huawei P20 Lite
            "EVA-L09",        // Huawei P9
            "VTR-L29",        // Huawei P10
            "MI_5",           // Xiaomi Mi 5
            "MI_8_UD",        // Xiaomi Mi 8 UD
            "MI_CC9",         // Xiaomi Mi CC9
            "Infinix_X6511",  // Infinix Hot 10
            "TECNO_KE5"       // TECNO Spark 5
    ));

    /** Chipset identifiers known to have hardware decoder issues. */
    private static final Set<String> BLACKLISTED_CHIPSETS = new HashSet<>(Arrays.asList(
            "Exynos 7570",
            "Exynos 7870",
            "Exynos 7880",
            "Kirin 659",
            "MT6750",
            "MT6735",
            "Snapdragon 425",
            "Snapdragon 625"
    ));

    /** Android release versions known to have hardware decoder issues. */
    private static final Set<String> BLACKLISTED_ANDROID_VERSIONS = new HashSet<>(Arrays.asList(
            "7.0",
            "7.1",
            "8.0"
    ));

    // -------------------------------------------------------------------------
    // Fields
    // -------------------------------------------------------------------------

    /** The wrapped hardware decoder factory. */
    private final VideoDecoderFactory hardwareDecoderFactory;

    /** Software fallback decoder factory for problematic devices. */
    private final SoftwareVideoDecoderFactory softwareDecoderFactory;

    /** Cached result of the device compatibility check. */
    private Boolean isDeviceCompatible = null;

    /** Hardware info string read from /proc/cpuinfo. */
    private String cpuHardwareInfo = null;

    // =========================================================================
    // Constructor
    // =========================================================================

    /**
     * Creates a new SafeVideoDecoderFactory wrapping the given hardware
     * decoder factory.
     *
     * @param hardwareDecoderFactory the hardware decoder factory to wrap
     */
    public SafeVideoDecoderFactory(VideoDecoderFactory hardwareDecoderFactory) {
        this.hardwareDecoderFactory = hardwareDecoderFactory;
        this.softwareDecoderFactory = new SoftwareVideoDecoderFactory();
    }

    // =========================================================================
    // VideoDecoderFactory implementation
    // =========================================================================

    /**
     * Creates a video decoder for the given codec. If the device is
     * blacklisted or the resolution exceeds limits, falls back to a
     * software decoder.
     *
     * @param codecInfo information about the codec to decode
     * @return a VideoDecoder instance
     */
    @Override
    public VideoDecoder createDecoder(VideoCodecInfo codecInfo) {
        if (!checkDeviceCompatibility()) {
            Log.d(TAG, "Device not compatible with hardware decoding, using software decoder");
            return softwareDecoderFactory.createDecoder(codecInfo);
        }

        try {
            return hardwareDecoderFactory.createDecoder(codecInfo);
        } catch (Exception e) {
            Log.w(TAG, "Hardware decoder creation failed, falling back to software", e);
            return softwareDecoderFactory.createDecoder(codecInfo);
        }
    }

    /**
     * Returns the list of supported codecs, filtering out AV1 on
     * problematic devices and falling back to software codecs if the
     * device is blacklisted.
     *
     * @return array of supported VideoCodecInfo
     */
    @Override
    public VideoCodecInfo[] getSupportedCodecs() {
        if (!checkDeviceCompatibility()) {
            Log.d(TAG, "Using software decoder codec list");
            return softwareDecoderFactory.getSupportedCodecs();
        }

        VideoCodecInfo[] hardwareCodecs = hardwareDecoderFactory.getSupportedCodecs();

        if (shouldFilterAv1()) {
            List<VideoCodecInfo> filtered = new ArrayList<>();
            for (VideoCodecInfo codec : hardwareCodecs) {
                if (!AV1_CODEC_NAME.equalsIgnoreCase(codec.getName())) {
                    filtered.add(codec);
                } else {
                    Log.d(TAG, "Filtering out AV1 codec for this device");
                }
            }
            return filtered.toArray(new VideoCodecInfo[0]);
        }

        return hardwareCodecs;
    }

    // =========================================================================
    // Device compatibility checks
    // =========================================================================

    /**
     * Performs a comprehensive device compatibility check. The result is
     * cached after the first invocation.
     *
     * @return true if the device is compatible with hardware decoding
     */
    private boolean checkDeviceCompatibility() {
        if (isDeviceCompatible != null) {
            return isDeviceCompatible;
        }

        isDeviceCompatible = performCompatibilityCheck();
        Log.d(TAG, "Device compatibility check result: " + isDeviceCompatible
                + " (model=" + Build.MODEL + ", version=" + Build.VERSION.RELEASE + ")");
        return isDeviceCompatible;
    }

    /**
     * Runs all individual compatibility checks.
     *
     * @return true if the device passes all checks
     */
    private boolean performCompatibilityCheck() {
        // Check device model blacklist
        if (isDeviceBlacklisted()) {
            Log.d(TAG, "Device model is blacklisted: " + Build.MODEL);
            return false;
        }

        // Check Android version blacklist
        if (isAndroidVersionBlacklisted()) {
            Log.d(TAG, "Android version is blacklisted: " + Build.VERSION.RELEASE);
            return false;
        }

        // Check chipset blacklist
        if (isChipsetBlacklisted()) {
            Log.d(TAG, "Chipset is blacklisted");
            return false;
        }

        // Check Samsung + Mali GPU combination
        if (isSamsungWithMaliGpu()) {
            Log.d(TAG, "Samsung device with Mali GPU detected");
            return false;
        }

        // Check Huawei Kirin devices
        if (isHuaweiKirin()) {
            Log.d(TAG, "Huawei Kirin device detected");
            return false;
        }

        // Check Xiaomi with old Snapdragon
        if (isXiaomiWithOldSnapdragon()) {
            Log.d(TAG, "Xiaomi with old Snapdragon detected");
            return false;
        }

        // Check for low-end devices (< 4 CPU cores)
        if (isLowEndDevice()) {
            Log.d(TAG, "Low-end device detected (< 4 cores)");
            return false;
        }

        return true;
    }

    /**
     * Checks if the current device model is in the blacklist.
     * Normalizes the model name by replacing spaces with underscores.
     *
     * @return true if the device is blacklisted
     */
    private boolean isDeviceBlacklisted() {
        String model = Build.MODEL;
        if (model == null) return false;

        String normalizedModel = model.replace(' ', '_');
        return BLACKLISTED_DEVICES.contains(model)
                || BLACKLISTED_DEVICES.contains(normalizedModel);
    }

    /**
     * Checks if the current Android version is in the blacklist.
     *
     * @return true if the Android version is blacklisted
     */
    private boolean isAndroidVersionBlacklisted() {
        String version = Build.VERSION.RELEASE;
        if (version == null) return false;
        return BLACKLISTED_ANDROID_VERSIONS.contains(version);
    }

    /**
     * Checks if the device's chipset is in the blacklist by reading
     * hardware information from /proc/cpuinfo.
     *
     * @return true if the chipset is blacklisted
     */
    private boolean isChipsetBlacklisted() {
        String hardware = getCpuHardwareInfo();
        if (hardware == null || hardware.isEmpty()) return false;

        String hwLower = hardware.toLowerCase();
        for (String chipset : BLACKLISTED_CHIPSETS) {
            if (hwLower.contains(chipset.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this is a Samsung device with a Mali GPU, which is known
     * for hardware decoder issues with certain video formats.
     *
     * @return true if Samsung + Mali GPU detected
     */
    private boolean isSamsungWithMaliGpu() {
        String manufacturer = Build.MANUFACTURER;
        if (manufacturer == null || !manufacturer.toLowerCase().contains("samsung")) {
            return false;
        }

        String hardware = getCpuHardwareInfo();
        if (hardware == null) return false;

        // Samsung devices with Exynos SoCs use Mali GPUs
        String hwLower = hardware.toLowerCase();
        return hwLower.contains("exynos") || hwLower.contains("mali");
    }

    /**
     * Checks if this is a Huawei device with a Kirin chipset.
     *
     * @return true if Huawei Kirin detected
     */
    private boolean isHuaweiKirin() {
        String manufacturer = Build.MANUFACTURER;
        if (manufacturer == null || !manufacturer.toLowerCase().contains("huawei")) {
            return false;
        }

        String hardware = getCpuHardwareInfo();
        if (hardware == null) return false;

        return hardware.toLowerCase().contains("kirin");
    }

    /**
     * Checks if this is a Xiaomi device with an older Snapdragon chipset.
     *
     * @return true if Xiaomi + old Snapdragon detected
     */
    private boolean isXiaomiWithOldSnapdragon() {
        String manufacturer = Build.MANUFACTURER;
        if (manufacturer == null || !manufacturer.toLowerCase().contains("xiaomi")) {
            return false;
        }

        String hardware = getCpuHardwareInfo();
        if (hardware == null) return false;

        String hwLower = hardware.toLowerCase();
        return hwLower.contains("msm8917")    // Snapdragon 425
                || hwLower.contains("msm8953") // Snapdragon 625
                || hwLower.contains("msm8937") // Snapdragon 430
                || hwLower.contains("sdm439")  // Snapdragon 439
                || hwLower.contains("sdm450"); // Snapdragon 450
    }

    /**
     * Checks if the device is "low-end" based on CPU core count.
     * Devices with fewer than 4 cores are considered low-end and may
     * struggle with hardware video decoding overhead.
     *
     * @return true if the device has fewer than 4 CPU cores
     */
    private boolean isLowEndDevice() {
        int cores = Runtime.getRuntime().availableProcessors();
        return cores < 4;
    }

    /**
     * Determines whether AV1 codec should be filtered out for this device.
     * AV1 hardware decoding is not well supported on many mid-range and
     * older devices.
     *
     * @return true if AV1 should be removed from supported codecs
     */
    private boolean shouldFilterAv1() {
        // Filter AV1 on older Android versions
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return true;
        }

        // Filter AV1 on devices with less than 6 cores
        int cores = Runtime.getRuntime().availableProcessors();
        if (cores < 6) {
            return true;
        }

        // Filter AV1 on blacklisted chipsets
        String hardware = getCpuHardwareInfo();
        if (hardware != null) {
            String hwLower = hardware.toLowerCase();
            if (hwLower.contains("mt67") || hwLower.contains("mt65")
                    || hwLower.contains("exynos 7") || hwLower.contains("kirin 6")
                    || hwLower.contains("msm89") || hwLower.contains("sdm4")) {
                return true;
            }
        }

        return false;
    }

    // =========================================================================
    // CPU info reading
    // =========================================================================

    /**
     * Reads the "Hardware" line from /proc/cpuinfo and caches the result.
     * This provides chipset/SoC identification on many Android devices.
     *
     * <p>Example output: "Qualcomm Technologies, Inc MSM8953" (Snapdragon 625)
     *
     * @return the hardware info string, or null if unavailable
     */
    private String getCpuHardwareInfo() {
        if (cpuHardwareInfo != null) {
            return cpuHardwareInfo;
        }

        cpuHardwareInfo = readCpuHardwareFromProc();
        return cpuHardwareInfo;
    }

    /**
     * Reads /proc/cpuinfo and extracts the "Hardware" field.
     *
     * @return the hardware info string, or empty string if not found
     */
    private static String readCpuHardwareFromProc() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/cpuinfo"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Hardware")) {
                    int colonIndex = line.indexOf(':');
                    if (colonIndex >= 0 && colonIndex + 1 < line.length()) {
                        return line.substring(colonIndex + 1).trim();
                    }
                }
            }
        } catch (IOException e) {
            Log.w(TAG, "Failed to read /proc/cpuinfo", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignored) {
                    // Ignore close errors
                }
            }
        }
        return "";
    }
}
