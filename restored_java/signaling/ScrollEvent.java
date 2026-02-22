package signaling;

import org.json.JSONObject;

/**
 * Represents a scroll event in the signaling protocol.
 * Carries horizontal and vertical scroll deltas to replicate
 * the user's scroll gesture on the remote display.
 */
public class ScrollEvent implements JsonSerializable {

    private final double deltaX;
    private final double deltaY;

    /**
     * Constructs a new ScrollEvent with the given scroll deltas.
     *
     * @param deltaX the horizontal scroll delta
     * @param deltaY the vertical scroll delta
     */
    public ScrollEvent(double deltaX, double deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * Returns the horizontal scroll delta.
     *
     * @return horizontal scroll amount
     */
    public double getDeltaX() {
        return deltaX;
    }

    /**
     * Returns the vertical scroll delta.
     *
     * @return vertical scroll amount
     */
    public double getDeltaY() {
        return deltaY;
    }

    /**
     * Deserializes a ScrollEvent from a JSON object.
     *
     * @param json the JSON object containing scroll event data
     * @return a new ScrollEvent instance
     */
    public static ScrollEvent fromJSONObject(JSONObject json) {
        double x = json.optDouble("delta_x", 0.0);
        double y = json.optDouble("delta_y", 0.0);
        return new ScrollEvent(x, y);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("delta_x", deltaX);
        json.put("delta_y", deltaY);
        return json;
    }
}
