package llIIIIlIlllIII1;

import android.opengl.GLES20;
import android.os.Build;
import c13.nim5.ez8.h5_proto.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.webrtc.EglBase;
import org.webrtc.EncodedImage;
import org.webrtc.SoftwareVideoDecoderFactory;
import org.webrtc.VideoCodecInfo;
import org.webrtc.VideoCodecStatus;
import org.webrtc.VideoDecoder;
import org.webrtc.VideoDecoderFactory;

/* loaded from: classes.dex */
public final class lIIIIlllllIlll1 implements VideoDecoderFactory {

    /* renamed from: IlIlllIIlI1, reason: collision with root package name */
    public final String[] f589IlIlllIIlI1;

    /* renamed from: IllIIlIIII1, reason: collision with root package name */
    public final String[] f590IllIIlIIII1;

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public final EglBase.Context f591lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public final VideoDecoderFactory f592llllIIIIll1;

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public final Set<String> f593llllIllIl1;

    public static final class llllIIIIll1 implements VideoDecoder {

        /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
        public final /* synthetic */ VideoDecoder f594lIIIIlllllIlll1;

        /* renamed from: llllIIIIll1, reason: collision with root package name */
        public final /* synthetic */ VideoDecoder f595llllIIIIll1;

        public llllIIIIll1(VideoDecoder videoDecoder) {
            this.f594lIIIIlllllIlll1 = videoDecoder;
            this.f595llllIIIIll1 = videoDecoder;
        }

        public VideoCodecStatus decode(EncodedImage encodedImage, VideoDecoder.DecodeInfo decodeInfo) {
            return this.f595llllIIIIll1.decode(encodedImage, decodeInfo);
        }

        public String getImplementationName() {
            return this.f595llllIIIIll1.getImplementationName();
        }

        public VideoCodecStatus initDecode(VideoDecoder.Settings settings, VideoDecoder.Callback callback) {
            byte[] bArr = {64, -127, -49, -7, 27, 84, ByteCompanionObject.MAX_VALUE, -70};
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            Intrinsics.checkNotNullParameter(settings, lllliiiill1.llllIIIIll1(new byte[]{51, -28, -69, -115, 114, 58, 24, -55}, bArr));
            Intrinsics.checkNotNullParameter(callback, lllliiiill1.llllIIIIll1(new byte[]{-67, -99, -12, -24, 122, 105, -88, 17}, new byte[]{-34, -4, -104, -124, 24, 8, -53, 122}));
            return this.f594lIIIIlllllIlll1.initDecode(new VideoDecoder.Settings(settings.numberOfCores, Math.min(settings.width, 1280), Math.min(settings.height, 720)), callback);
        }

        public VideoCodecStatus release() {
            return this.f595llllIIIIll1.release();
        }
    }

    public lIIIIlllllIlll1(VideoDecoderFactory videoDecoderFactory, EglBase.Context context) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(videoDecoderFactory, lllliiiill1.llllIIIIll1(new byte[]{-55, 107, 4, 46, 59, 76, 47, -90, -59, 109, 2, 59, 43}, new byte[]{-90, 25, 109, 73, 82, 34, 105, -57}));
        Intrinsics.checkNotNullParameter(context, lllliiiill1.llllIIIIll1(new byte[]{25, -5, -23, -11, 30, -88, 54, 4, 4, -24}, new byte[]{124, -100, -123, -74, 113, -58, 66, 97}));
        this.f592llllIIIIll1 = videoDecoderFactory;
        this.f591lIIIIlllllIlll1 = context;
        this.f593llllIllIl1 = SetsKt.setOf((Object[]) new String[]{lllliiiill1.llllIIIIll1(new byte[]{26, 74, 102, -61, -4, -114, 21, -41}, new byte[]{73, 7, 75, -124, -59, -67, 37, -111}), lllliiiill1.llllIIIIll1(new byte[]{36, -74, 105, -19, -80, -39, -34, 57}, new byte[]{119, -5, 68, -86, -119, -20, -18, ByteCompanionObject.MAX_VALUE}), lllliiiill1.llllIIIIll1(new byte[]{-124, -74, -34, -33, 9, 77, -93, 38}, new byte[]{-41, -5, -13, -107, 62, 126, -109, 96}), lllliiiill1.llllIIIIll1(new byte[]{80, -121, -71, 116, 56, 86, -113, 104}, new byte[]{3, -54, -108, 53, 13, 100, -65, 46}), lllliiiill1.llllIIIIll1(new byte[]{101, 69, 86, 61, -100, -66, 16, 55, 31, 32, 72, 38, -80, -125, 42}, new byte[]{45, 16, 23, 106, -39, -9, 79, 103}), lllliiiill1.llllIIIIll1(new byte[]{-12, -88, -89, -27, 57, 23, 3}, new byte[]{-79, -2, -26, -56, 117, 39, 58, 83}), lllliiiill1.llllIIIIll1(new byte[]{113, 38, 22, 124, -58, 9, 31}, new byte[]{39, 114, 68, 81, -118, 59, 38, 55}), lllliiiill1.llllIIIIll1(new byte[]{70, 18, 11, 62}, new byte[]{11, 91, 84, 11, 84, -10, 87, -19}), lllliiiill1.llllIIIIll1(new byte[]{20, -28, -24, -115, -53, -1, -9}, new byte[]{89, -83, -73, -75, -108, -86, -77, 72}), lllliiiill1.llllIIIIll1(new byte[]{101, -17, 11, 92, 103, -60}, new byte[]{40, -90, 84, 31, 36, -3, 115, 102}), lllliiiill1.llllIIIIll1(new byte[]{120, -23, 97, 121, 48, -78, 17, -7, 105, -79, 50, 33, 111}, new byte[]{49, -121, 7, 16, 94, -37, 105, -90}), lllliiiill1.llllIIIIll1(new byte[]{-53, 81, 126, 5, -77, -72, -127, -101, -86}, new byte[]{-97, 20, 61, 75, -4, -25, -54, -34})});
        this.f590IllIIlIIII1 = new String[]{lllliiiill1.llllIIIIll1(new byte[]{-70, -56, -24, 91, 46, -15, 116, -8, -54, -121, -95}, new byte[]{-1, -80, -111, 53, 65, -126, 84, -49}), lllliiiill1.llllIIIIll1(new byte[]{73, -55, 108, 20, 12, 30, 119, -23, 52, -122, 37}, new byte[]{12, -79, 21, 122, 99, 109, 87, -34}), lllliiiill1.llllIIIIll1(new byte[]{-4, 59, 82, -127, -109, -3, -26, 4, -127, 123, 27}, new byte[]{-71, 67, 43, -17, -4, -114, -58, 51}), lllliiiill1.llllIIIIll1(new byte[]{12, -20, 18, -13, -98, 75, 104, 80, 126}, new byte[]{71, -123, 96, -102, -16, 107, 94, 101}), lllliiiill1.llllIIIIll1(new byte[]{-7, 124, -91, 72, -4, -62}, new byte[]{-76, 40, -109, ByteCompanionObject.MAX_VALUE, -55, -14, 78, 66}), lllliiiill1.llllIIIIll1(new byte[]{33, 113, 115, 121, 71, 119}, new byte[]{108, 37, 69, 78, 116, 66, -115, 37}), lllliiiill1.llllIIIIll1(new byte[]{72, -61, 36, -82, 124, -37, -93, -23, 116, -61, 101, -22, 42, -100}, new byte[]{27, -83, 69, -34, 24, -87, -62, -114}), lllliiiill1.llllIIIIll1(new byte[]{-122, -59, 111, -123, -114, 68, 56, -79, -70, -59, 46, -61, -40, 3}, new byte[]{-43, -85, 14, -11, -22, 54, 89, -42})};
        this.f589IlIlllIIlI1 = new String[]{lllliiiill1.llllIIIIll1(new byte[]{-49, -21, 44}, new byte[]{-8, -59, 28, -123, -66, -61, 10, 88}), lllliiiill1.llllIIIIll1(new byte[]{72, -17, -12}, new byte[]{ByteCompanionObject.MAX_VALUE, -63, -59, 64, -38, 45, 89, 28}), lllliiiill1.llllIIIIll1(new byte[]{-70, -65, -36}, new byte[]{-126, -111, -20, 28, -39, 48, -40, 65})};
    }

    public final void IlIlIIlIII1() {
        Pair<Boolean, String> lIllIIIlIl12 = lIllIIIlIl1();
        boolean booleanValue = lIllIIIlIl12.component1().booleanValue();
        String component2 = lIllIIIlIl12.component2();
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        lllliiiill1.llllIIIIll1(new byte[]{79, -60, -5, 46, 50, 63, 79, -17, 99, -60, -5, 42}, new byte[]{11, -95, -104, 65, 86, 90, 61, -84});
        StringBuilder append = new StringBuilder().append(lllliiiill1.llllIIIIll1(new byte[]{68, 120, ByteCompanionObject.MAX_VALUE, 21, -40, 125, 53, -94, 110, 120, ByteCompanionObject.MAX_VALUE, 21, -40, 25, 112, -12, 39, 59, 58, 21, -79, 51, 115, -19, 116, 82, ByteCompanionObject.MAX_VALUE, 21, -40, 125, 53, -94, 110, 120, ByteCompanionObject.MAX_VALUE, 21, -40, 125, 88, -19, 42, 61, 51, 15, -40}, new byte[]{78, 88, 95, 53, -8, 93, 21, -126})).append(Build.MODEL).append(lllliiiill1.llllIIIIll1(new byte[]{-126, -116, -12, 57, 59, -86, 59, 120, -88, -116, -12, 57, 59, -56, 105, 57, -26, -56, -18, 57}, new byte[]{-120, -84, -44, 25, 27, -118, 27, 88})).append(Build.BRAND).append(lllliiiill1.llllIIIIll1(new byte[]{79, -13, -57, -111, -96, -71, 60, -44, 101, -13, -57, -111, -96, -54, 83, -73, ByteCompanionObject.MAX_VALUE, -13}, new byte[]{69, -45, -25, -79, ByteCompanionObject.MIN_VALUE, -103, 28, -12})).append(llllIIIIll1()).append(lllliiiill1.llllIIIIll1(new byte[]{36, -75, 98, -115, -89, 100, -5, -70, 14, -75, 98, -115, -89, 5, -75, -2, 92, -6, 43, -55, -67, 100}, new byte[]{46, -107, 66, -83, -121, 68, -37, -102})).append(Build.VERSION.RELEASE).append(lllliiiill1.llllIIIIll1(new byte[]{-42, -69, 105, 29, 27, 53, 57, 115, -4, -69, 105, 29, 27, 82, 73, 6, -26, -69}, new byte[]{-36, -101, 73, 61, 59, 21, 25, 83})).append(GLES20.glGetString(7937)).append(lllliiiill1.llllIIIIll1(new byte[]{-23, -38, -46, 54, 92, 63, -87, 84, -61, -38, -46, 54, 92, 89, -26, 6, ByteCompanionObject.MIN_VALUE, -97, -46, 69, 43, 37, -87}, new byte[]{-29, -6, -14, 22, 124, 31, -119, 116})).append(booleanValue).append(lllliiiill1.llllIIIIll1(new byte[]{-14, -111, 27, -92, 35, -100, 34, 69, -40, -111, 27, -92, 35, -18, 103, 4, -117, -34, 85, -66, 35}, new byte[]{-8, -79, 59, -124, 3, -68, 2, 101}));
        if (component2.length() == 0) {
            component2 = lllliiiill1.llllIIIIll1(new byte[]{-62, 75, 34, -107}, new byte[]{-116, 36, 76, -16, 87, 38, -24, ByteCompanionObject.MAX_VALUE});
        }
        StringsKt.trimIndent(append.append(component2).append("\n        ").toString());
    }

    public final boolean IlIllIlllIllI1() {
        return StringsKt.contains((CharSequence) llllIIIIll1(), (CharSequence) IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-22, 72, -127, -122, -91, -85, 105, 2, -42, 72}, new byte[]{-71, 38, -32, -10, -63, -39, 8, 101}), true) && Build.VERSION.SDK_INT < 27;
    }

    public final boolean IlIlllIIlI1() {
        String str = Build.HARDWARE;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullExpressionValue(str, lllliiiill1.llllIIIIll1(new byte[]{105, 30, -57, -27, -32, -96, -48, 17}, new byte[]{33, 95, -107, -95, -73, -31, -126, 84}));
        Locale locale = Locale.ROOT;
        String lowerCase = str.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, lllliiiill1.llllIIIIll1(new byte[]{85, -71, 61, -90, ByteCompanionObject.MIN_VALUE, -111, -98, 10, 64, -91, 20, -31, -39, -38, -62, 96}, new byte[]{33, -42, 113, -55, -9, -12, -20, 73}));
        if (!StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lllliiiill1.llllIIIIll1(new byte[]{-68, 96}, new byte[]{-47, 20, 22, 1, -5, -20, -46, -76}), false, 2, (Object) null)) {
            String str2 = Build.BOARD;
            Intrinsics.checkNotNullExpressionValue(str2, lllliiiill1.llllIIIIll1(new byte[]{-60, -76, 118, -38, -54}, new byte[]{-122, -5, 55, -120, -114, -91, -83, 119}));
            String lowerCase2 = str2.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, lllliiiill1.llllIIIIll1(new byte[]{-104, 59, 12, -28, 81, -45, 119, 71, -115, 39, 37, -93, 8, -104, 43, 45}, new byte[]{-20, 84, 64, -117, 38, -74, 5, 4}));
            if (!StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lllliiiill1.llllIIIIll1(new byte[]{-91, 61}, new byte[]{-56, 73, 95, -54, 26, -39, 65, -106}), false, 2, (Object) null)) {
                return false;
            }
        }
        return true;
    }

    public final boolean IllIIlIIII1() {
        return Runtime.getRuntime().availableProcessors() < 4;
    }

    public VideoDecoder createDecoder(VideoCodecInfo videoCodecInfo) {
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        Intrinsics.checkNotNullParameter(videoCodecInfo, lllliiiill1.llllIIIIll1(new byte[]{46, 123, -88, -35, -39, 31, -102, 69, 61, 113, -123, -42, -48, 51}, new byte[]{88, 18, -52, -72, -74, 92, -11, 33}));
        Pair<Boolean, String> lIllIIIlIl12 = lIllIIIlIl1();
        if (lIllIIIlIl12.getFirst().booleanValue()) {
            lllllIllIl1.IllIIlIIII1.lIIIIlllllIlll1(Log.LogLevel.INFO, "", lllliiiill1.llllIIIIll1(new byte[]{22, 74, 69, 89, -28, 122, 106, 81, 80, 42, 126, 40, -105, 114, 3, 3, 67, 87}, new byte[]{-1, -51, -62, -66, 112, -46, -126, -20}) + lIllIIIlIl12.getSecond());
        }
        if (!lIIIIlllllIlll1() && !lIllIIIlIl12.getFirst().booleanValue()) {
            VideoDecoder createDecoder = this.f592llllIIIIll1.createDecoder(videoCodecInfo);
            if (createDecoder != null) {
                return new llllIIIIll1(createDecoder);
            }
            return null;
        }
        return new SoftwareVideoDecoderFactory().createDecoder(videoCodecInfo);
    }

    public VideoCodecInfo[] getSupportedCodecs() {
        VideoCodecInfo[] supportedCodecs = this.f592llllIIIIll1.getSupportedCodecs();
        Intrinsics.checkNotNullExpressionValue(supportedCodecs, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-109, -91, 17, -122, -17, 75, -122, -34, -122, -76, 0, -79, -39, 84, -110, -44, -105, -77, 77, -5, -76, 21, -33}, new byte[]{-12, -64, 101, -43, -102, 59, -10, -79}));
        ArrayList arrayList = new ArrayList();
        for (VideoCodecInfo videoCodecInfo : supportedCodecs) {
            Intrinsics.checkNotNull(videoCodecInfo);
            if (!llllIIIIll1(videoCodecInfo)) {
                arrayList.add(videoCodecInfo);
            }
        }
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[0]);
    }

    public final boolean lIIIIlllllIlll1() {
        String str = Build.MANUFACTURER;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        if (StringsKt.equals(str, lllliiiill1.llllIIIIll1(new byte[]{-118, 50, 91, -47, -39, 113}, new byte[]{-62, 103, 26, -122, -100, 56, 126, -100}), true)) {
            String str2 = Build.MODEL;
            Intrinsics.checkNotNullExpressionValue(str2, lllliiiill1.llllIIIIll1(new byte[]{118, -24, -81, -7, 82}, new byte[]{59, -89, -21, -68, 30, 25, 107, -15}));
            if (StringsKt.startsWith$default(str2, lllliiiill1.llllIIIIll1(new byte[]{-106, -57, -109, 56}, new byte[]{-58, -107, -46, 21, -46, -117, -126, -49}), false, 2, (Object) null)) {
                return true;
            }
            Intrinsics.checkNotNullExpressionValue(str2, lllliiiill1.llllIIIIll1(new byte[]{24, -76, -14, 31, 122}, new byte[]{85, -5, -74, 90, 54, 32, 18, -1}));
            if (StringsKt.startsWith$default(str2, lllliiiill1.llllIIIIll1(new byte[]{81, 74, 18, 95}, new byte[]{20, 28, 83, 114, -111, -29, 9, 107}), false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public final Pair<Boolean, String> lIllIIIlIl1() {
        ArrayList arrayList = new ArrayList();
        Set<String> set = this.f593llllIllIl1;
        String str = Build.MODEL;
        if (set.contains(str)) {
            StringBuilder sb = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            arrayList.add(sb.append(lllliiiill1.llllIIIIll1(new byte[]{-90, 117, 63, 51, 48, 90}, new byte[]{-21, 26, 91, 86, 92, 122, 116, 89})).append(str).append(lllliiiill1.llllIIIIll1(new byte[]{103, 86, -93, -35, 4, 36, -26, -92, 44, 83, -92, -114, 18}, new byte[]{71, 63, -51, -3, 102, 72, -121, -57})).toString());
            return TuplesKt.to(Boolean.TRUE, CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null));
        }
        String llllIIIIll12 = llllIIIIll1();
        for (String str2 : this.f590IllIIlIIII1) {
            if (StringsKt.contains((CharSequence) llllIIIIll12, (CharSequence) str2, true)) {
                StringBuilder sb2 = new StringBuilder();
                IllIIlIIII1.llllIIIIll1 lllliiiill12 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
                arrayList.add(sb2.append(lllliiiill12.llllIIIIll1(new byte[]{-12, 113, -75, -60, -38, -62, 86, 75}, new byte[]{-73, 25, -36, -76, -87, -89, 34, 107})).append(llllIIIIll12).append(lllliiiill12.llllIIIIll1(new byte[]{14, 124, 33, 46, 39, 102, -37, -23, 69, 121, 38, 125, 49}, new byte[]{46, 21, 79, 14, 69, 10, -70, -118})).toString());
                return TuplesKt.to(Boolean.TRUE, CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null));
            }
        }
        String[] strArr = this.f589IlIlllIIlI1;
        String str3 = Build.VERSION.RELEASE;
        if (ArraysKt.contains(strArr, str3)) {
            StringBuilder sb3 = new StringBuilder();
            IllIIlIIII1.llllIIIIll1 lllliiiill13 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
            arrayList.add(sb3.append(lllliiiill13.llllIIIIll1(new byte[]{-3, -90, -91, -75, -98, -41, -33, 105}, new byte[]{-68, -56, -63, -57, -15, -66, -69, 73})).append(str3).append(lllliiiill13.llllIIIIll1(new byte[]{101, -110, 0, -40, 12, 24, 40, -93, 46, -105, 7, -117, 26}, new byte[]{69, -5, 110, -8, 110, 116, 73, -64})).toString());
            return TuplesKt.to(Boolean.TRUE, CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null));
        }
        if (llllllIlIIIlll1()) {
            arrayList.add(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{96, 3, 79, 98, 125, 26, 45, -27, 87, 7, 84, 120, 107, 17, 106, -78, 90, 22, 74, 49, 69, 21, 38, -84, 19, 37, 114, 68}, new byte[]{51, 98, 34, 17, 8, 116, 74, -59}));
            return TuplesKt.to(Boolean.TRUE, CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null));
        }
        if (llllIllIl1()) {
            arrayList.add(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-31, -60, 100, 46, 113, -98, -16, 124, -52, -42, 100, 58, 109, -41, -101, 121, -37, -40, 107, 121, 119, -97, -71, 96, -38, -44, 113}, new byte[]{-87, -79, 5, 89, 20, -9, -48, 16}));
            return TuplesKt.to(Boolean.TRUE, CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null));
        }
        if (IlIllIlllIllI1()) {
            arrayList.add(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-34, -88, 46, 73, -78, -127, -64, 46, -17, -75, 39, 6, -80, -124, -124, 121, -43, -81, 46, 86, -69, -102, -127, 62, -23, -81}, new byte[]{-122, -63, 79, 38, -33, -24, -32, 89}));
            return TuplesKt.to(Boolean.TRUE, CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null));
        }
        if (IllIIlIIII1()) {
            arrayList.add(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-64, 5, -97, 4, -30, 22, -45, -21, -24, 15, -98, 64, -28, 29, -105, -29, -34, 43, -91, 9, -69, 74, -16, -119, -96, 74, -85, 121, -46, 88, -117, -1, -84, 9, -121, 91, -30, 11, -98}, new byte[]{-116, 106, -24, 41, -121, 120, -73, -53}));
            return TuplesKt.to(Boolean.TRUE, CollectionsKt.joinToString$default(arrayList, null, null, null, 0, null, null, 63, null));
        }
        return TuplesKt.to(Boolean.FALSE, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-24, -44, -91, -113, 120, 74, 63, -108, -43, -101, -31, -125, ByteCompanionObject.MAX_VALUE, 92, 41, -123, -61, -33}, new byte[]{-90, -69, -123, -26, 11, 57, 74, -15}));
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x002a, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r6.name, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-92, -115, -68}, new byte[]{-27, -37, -115, 52, -10, 22, 25, 30})) != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean llllIIIIll1(org.webrtc.VideoCodecInfo r6) {
        /*
            r5 = this;
            IlIlIIIlIlIlll1.llllllIlIIIlll1 r0 = new IlIlIIIlIlIlll1.llllllIlIIIlll1
            r0.<init>()
            java.lang.String r0 = r0.llllIIIIll1()
            java.lang.String r1 = IlIlIIIlIlIlll1.llllllIlIIIlll1.f160lIIIIlllllIlll1
            boolean r0 = r0.equals(r1)
            r1 = 8
            if (r0 == 0) goto L2d
            java.lang.String r0 = r6.name
            r2 = 3
            byte[] r2 = new byte[r2]
            r2 = {x0050: FILL_ARRAY_DATA , data: [-92, -115, -68} // fill-array
            byte[] r3 = new byte[r1]
            r3 = {x0056: FILL_ARRAY_DATA , data: [-27, -37, -115, 52, -10, 22, 25, 30} // fill-array
            IllIIlIIII1.llllIIIIll1 r4 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1
            java.lang.String r2 = r4.llllIIIIll1(r2, r3)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r0 == 0) goto L2d
            goto L4c
        L2d:
            boolean r0 = r5.IlIlllIIlI1()
            if (r0 == 0) goto L4e
            java.lang.String r6 = r6.name
            r0 = 4
            byte[] r0 = new byte[r0]
            r0 = {x005e: FILL_ARRAY_DATA , data: [-60, -3, -6, -84} // fill-array
            byte[] r1 = new byte[r1]
            r1 = {x0064: FILL_ARRAY_DATA , data: [-116, -49, -52, -103, 5, 82, -88, 104} // fill-array
            IllIIlIIII1.llllIIIIll1 r2 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1
            java.lang.String r0 = r2.llllIIIIll1(r0, r1)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            if (r6 == 0) goto L4e
        L4c:
            r6 = 1
            goto L4f
        L4e:
            r6 = 0
        L4f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: llIIIIlIlllIII1.lIIIIlllllIlll1.llllIIIIll1(org.webrtc.VideoCodecInfo):boolean");
    }

    public final boolean llllIllIl1() {
        String str = Build.BRAND;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        return StringsKt.equals(str, lllliiiill1.llllIIIIll1(new byte[]{82, 118, 96, -40, -121, -78}, new byte[]{26, 3, 1, -81, -30, -37, -15, -16}), true) && StringsKt.contains((CharSequence) llllIIIIll1(), (CharSequence) lllliiiill1.llllIIIIll1(new byte[]{0, -90, -50, 5, -18}, new byte[]{75, -49, -68, 108, ByteCompanionObject.MIN_VALUE, 107, 103, 56}), true) && Build.VERSION.SDK_INT < 28;
    }

    public final boolean llllllIlIIIlll1() {
        String str = Build.BRAND;
        IllIIlIIII1.llllIIIIll1 lllliiiill1 = IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1;
        if (StringsKt.equals(str, lllliiiill1.llllIIIIll1(new byte[]{103, 66, 123, -6, -10, 45, ByteCompanionObject.MAX_VALUE}, new byte[]{20, 35, 22, -119, -125, 67, 24, 29}), true)) {
            String glGetString = GLES20.glGetString(7937);
            Intrinsics.checkNotNullExpressionValue(glGetString, lllliiiill1.llllIIIIll1(new byte[]{-81, 22, 118, 17, -69, 64, 22, 124, -95, 20, 86, 92, -31, 61, 76, 39}, new byte[]{-56, 122, 49, 116, -49, 19, 98, 14}));
            if (StringsKt.contains((CharSequence) glGetString, (CharSequence) lllliiiill1.llllIIIIll1(new byte[]{-81, -115, 7, -30}, new byte[]{-30, -20, 107, -117, -38, 59, 2, 39}), true)) {
                return true;
            }
        }
        return false;
    }

    public final VideoDecoder llllIIIIll1(VideoDecoder videoDecoder) {
        return new llllIIIIll1(videoDecoder);
    }

    public final String llllIIIIll1() {
        String str;
        try {
            Iterator<String> it = StringsKt.lineSequence(FilesKt.readText$default(new File(IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-100, -99, 105, 29, -113, 32, -34, -61, -58, -124, 117, 20, -125}, new byte[]{-77, -19, 27, 114, -20, 15, -67, -77})), null, 1, null)).iterator();
            while (true) {
                if (!it.hasNext()) {
                    str = null;
                    break;
                }
                str = it.next();
                if (StringsKt.startsWith$default(str, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{-33, 122, -70, 23, 78, -114, -86, 22}, new byte[]{-105, 27, -56, 115, 57, -17, -40, 115}), false, 2, (Object) null)) {
                    break;
                }
            }
            String str2 = str;
            if (str2 != null) {
                String substringAfter$default = StringsKt.substringAfter$default(str2, IlIlllIIlI1.IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{24}, new byte[]{34, -20, 112, 104, 94, 111, 72, 89}), (String) null, 2, (Object) null);
                if (substringAfter$default != null && (r0 = StringsKt.trim((CharSequence) substringAfter$default).toString()) != null) {
                    Intrinsics.checkNotNull(r0);
                    return r0;
                }
            }
            String str3 = Build.HARDWARE;
            Intrinsics.checkNotNull(str3);
            return str3;
        } catch (Exception unused) {
            String str4 = Build.HARDWARE;
            Intrinsics.checkNotNull(str4);
            return str4;
        }
    }
}
