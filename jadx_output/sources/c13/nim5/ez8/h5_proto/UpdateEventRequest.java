package c13.nim5.ez8.h5_proto;

import IlIlllIIlI1.IllIIlIIII1;
import IllIIlIIII1.llllIIIIll1;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class UpdateEventRequest {
    private Atom atom;
    private List<Event> events;

    public UpdateEventRequest() {
        this.atom = new Atom();
        this.events = new ArrayList();
        this.atom = new Atom();
        this.events = new ArrayList();
    }

    public static UpdateEventRequest fromJSONObject(JSONObject jSONObject) throws JSONException {
        UpdateEventRequest updateEventRequest = new UpdateEventRequest();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{-29, -124, -15, 35}, new byte[]{-126, -16, -98, 78, 103, -25, 15, 37})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{46, -97, 94, 85}, new byte[]{79, -21, 49, 56, 83, 51, -39, -13}))) {
            updateEventRequest.atom = Atom.fromJSONObject(jSONObject.getJSONObject(lllliiiill1.llllIIIIll1(new byte[]{-47, 56, 72, 81}, new byte[]{-80, 76, 39, 60, -70, -58, 112, 44})));
        }
        if (jSONObject.has(lllliiiill1.llllIIIIll1(new byte[]{38, -78, -120, 48, -84, -54}, new byte[]{67, -60, -19, 94, -40, -71, 17, -108})) && !jSONObject.isNull(lllliiiill1.llllIIIIll1(new byte[]{4, -110, 46, -56, 30, -106}, new byte[]{97, -28, 75, -90, 106, -27, -101, -89}))) {
            JSONArray jSONArray = jSONObject.getJSONArray(lllliiiill1.llllIIIIll1(new byte[]{64, 75, -87, 97, -115, 126}, new byte[]{37, 61, -52, 15, -7, 13, -97, 97}));
            updateEventRequest.events = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                updateEventRequest.events.add(Event.fromJSONObject(jSONArray.getJSONObject(i)));
            }
        }
        return updateEventRequest;
    }

    public Atom getAtom() {
        return this.atom;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setAtom(Atom atom) {
        this.atom = atom;
    }

    public void setEvents(List<Event> list) {
        this.events = list;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.atom != null) {
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{62, 89, 34, 65}, new byte[]{95, 45, 77, 44, -14, 69, 20, -30}), this.atom.toJSONObject());
        }
        if (this.events != null) {
            JSONArray jSONArray = new JSONArray();
            for (Event event : this.events) {
                if (event instanceof Event) {
                    jSONArray.put(event.toJSONObject());
                } else {
                    jSONArray.put(event);
                }
            }
            jSONObject.put(IllIIlIIII1.f243llllIIIIll1.llllIIIIll1(new byte[]{24, 68, 77, -107, -15, 41}, new byte[]{125, 50, 40, -5, -123, 90, 122, 82}), jSONArray);
        }
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        llllIIIIll1 lllliiiill1 = IllIIlIIII1.f243llllIIIIll1;
        return sb.append(lllliiiill1.llllIIIIll1(new byte[]{70, -48, 21, -53, -35, -123, -90, 27, 118, -50, 5, -8, -52, -111, -106, 8, 96, -44, 10, -53, -35, -113, -114, 80}, new byte[]{19, -96, 113, -86, -87, -32, -29, 109})).append(this.atom).append(lllliiiill1.llllIIIIll1(new byte[]{-109, 24, -55, 5, 21, -20, -54, -56, -126}, new byte[]{-65, 56, -84, 115, 112, -126, -66, -69})).append(this.events).append(lllliiiill1.llllIIIIll1(new byte[]{42}, new byte[]{87, -9, -87, 56, 51, -107, 87, 24})).toString();
    }
}
