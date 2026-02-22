package signaling;

import org.json.JSONObject;

/**
 * Represents a click (tap) event in the signaling protocol.
 * Carries normalized screen coordinates (0.0 to 1.0) indicating
 * where the user clicked on the remote display.
 */
public class ClickEvent implements JsonSerializable {

    private final double normalizedX;
    private final double normalizedY;

    /**
     * Constructs a new ClickEvent with the given normalized coordinates.
     *
     * @param normalizedX the normalized x-coordinate (0.0 to 1.0)
     * @param normalizedY the normalized y-coordinate (0.0 to 1.0)
     */
    public ClickEvent(double normalizedX, double normalizedY) {
        this.normalizedX = normalizedX;
        this.normalizedY = normalizedY;
    }

    /**
     * Returns the normalized x-coordinate of the click.
     *
     * @return normalized x value between 0.0 and 1.0
     */
    public double getNormalizedX() {
        return normalizedX;
    }

    /**
     * Returns the normalized y-coordinate of the click.
     *
     * @return normalized y value between 0.0 and 1.0
     */
    public double getNormalizedY() {
        return normalizedY;
    }

    /**
     * Deserializes a ClickEvent from a JSON object.
     *
     * @param json the JSON object containing click event data
     * @return a new ClickEvent instance
     */
    public static ClickEvent fromJSONObject(JSONObject json) {
        double x = json.optDouble("normalized_x", 0.0);
        double y = json.optDouble("normalized_y", 0.0);
        return new ClickEvent(x, y);
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("normalized_x", normalizedX);
        json.put("normalized_y", normalizedY);
        return json;
    }
}
