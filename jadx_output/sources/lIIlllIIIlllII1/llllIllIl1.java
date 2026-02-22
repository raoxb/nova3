package lIIlllIIIlllII1;

import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class llllIllIl1 {

    /* renamed from: llllIllIl1, reason: collision with root package name */
    public static final String f380llllIllIl1 = "";

    /* renamed from: lIIIIlllllIlll1, reason: collision with root package name */
    public String f381lIIIIlllllIlll1;

    /* renamed from: llllIIIIll1, reason: collision with root package name */
    public Map<String, String> f382llllIIIIll1 = new LinkedHashMap();

    public static llllIllIl1 llllIIIIll1(String str) {
        String str2;
        llllIllIl1 llllillil1 = new llllIllIl1();
        String[] split = str.split(";");
        llllillil1.f381lIIIIlllllIlll1 = split[0].trim();
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].split("=");
            if (split2.length > 1) {
                str2 = split2[1].trim();
                if ((str2.startsWith("\"") && str2.endsWith("\"")) || (str2.startsWith("'") && str2.endsWith("'") && str2.length() > 2)) {
                    str2 = str2.substring(1, str2.length() - 1);
                }
            } else {
                str2 = "";
            }
            llllillil1.f382llllIIIIll1.put(split2[0].trim(), str2);
        }
        return llllillil1;
    }

    public Map<String, String> lIIIIlllllIlll1() {
        return this.f382llllIIIIll1;
    }

    public String llllIIIIll1() {
        return this.f381lIIIIlllllIlll1;
    }
}
