package signaling;

import org.json.JSONObject;

/**
 * Represents a remote control command in the signaling protocol.
 * Wraps one of three command types -- click, scroll, or text input --
 * and carries the corresponding event data. This is the envelope used
 * to send user interaction events to the remote session.
 */
public class ControlCommand implements JsonSerializable {

    /** The type identifier for click commands. */
    public static final String COMMAND_TYPE_CLICK = "click";

    /** The type identifier for scroll commands. */
    public static final String COMMAND_TYPE_SCROLL = "scroll";

    /** The type identifier for text input commands. */
    public static final String COMMAND_TYPE_INPUT = "input";

    private final String commandType;
    private ClickEvent clickEvent;
    private ScrollEvent scrollEvent;
    private TextInput textInput;

    /**
     * Constructs a ControlCommand for a click event.
     *
     * @param clickEvent the click event data
     * @return a new ControlCommand wrapping the click
     */
    public static ControlCommand ofClick(ClickEvent clickEvent) {
        ControlCommand cmd = new ControlCommand(COMMAND_TYPE_CLICK);
        cmd.clickEvent = clickEvent;
        return cmd;
    }

    /**
     * Constructs a ControlCommand for a scroll event.
     *
     * @param scrollEvent the scroll event data
     * @return a new ControlCommand wrapping the scroll
     */
    public static ControlCommand ofScroll(ScrollEvent scrollEvent) {
        ControlCommand cmd = new ControlCommand(COMMAND_TYPE_SCROLL);
        cmd.scrollEvent = scrollEvent;
        return cmd;
    }

    /**
     * Constructs a ControlCommand for a text input event.
     *
     * @param textInput the text input data
     * @return a new ControlCommand wrapping the input
     */
    public static ControlCommand ofInput(TextInput textInput) {
        ControlCommand cmd = new ControlCommand(COMMAND_TYPE_INPUT);
        cmd.textInput = textInput;
        return cmd;
    }

    private ControlCommand(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Returns the command type identifier.
     *
     * @return one of {@link #COMMAND_TYPE_CLICK}, {@link #COMMAND_TYPE_SCROLL},
     *         or {@link #COMMAND_TYPE_INPUT}
     */
    public String getCommandType() {
        return commandType;
    }

    /**
     * Returns the click event data, if this is a click command.
     *
     * @return the ClickEvent, or null if this is not a click command
     */
    public ClickEvent getClickEvent() {
        return clickEvent;
    }

    /**
     * Returns the scroll event data, if this is a scroll command.
     *
     * @return the ScrollEvent, or null if this is not a scroll command
     */
    public ScrollEvent getScrollEvent() {
        return scrollEvent;
    }

    /**
     * Returns the text input data, if this is an input command.
     *
     * @return the TextInput, or null if this is not an input command
     */
    public TextInput getTextInput() {
        return textInput;
    }

    /**
     * Deserializes a ControlCommand from a JSON object.
     *
     * @param json the JSON object containing control command data
     * @return a new ControlCommand instance, or null if the command type is unrecognized
     */
    public static ControlCommand fromJSONObject(JSONObject json) {
        String type = json.optString("command_type", "");
        switch (type) {
            case COMMAND_TYPE_CLICK: {
                JSONObject data = json.optJSONObject(COMMAND_TYPE_CLICK);
                ClickEvent event = data != null ? ClickEvent.fromJSONObject(data) : new ClickEvent(0, 0);
                return ofClick(event);
            }
            case COMMAND_TYPE_SCROLL: {
                JSONObject data = json.optJSONObject(COMMAND_TYPE_SCROLL);
                ScrollEvent event = data != null ? ScrollEvent.fromJSONObject(data) : new ScrollEvent(0, 0);
                return ofScroll(event);
            }
            case COMMAND_TYPE_INPUT: {
                JSONObject data = json.optJSONObject(COMMAND_TYPE_INPUT);
                TextInput event = data != null ? TextInput.fromJSONObject(data) : new TextInput("");
                return ofInput(event);
            }
            default:
                return null;
        }
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put("command_type", commandType);
        switch (commandType) {
            case COMMAND_TYPE_CLICK:
                if (clickEvent != null) {
                    json.put(COMMAND_TYPE_CLICK, clickEvent.toJSONObject());
                }
                break;
            case COMMAND_TYPE_SCROLL:
                if (scrollEvent != null) {
                    json.put(COMMAND_TYPE_SCROLL, scrollEvent.toJSONObject());
                }
                break;
            case COMMAND_TYPE_INPUT:
                if (textInput != null) {
                    json.put(COMMAND_TYPE_INPUT, textInput.toJSONObject());
                }
                break;
        }
        return json;
    }
}
