package CommandPattern;
import org.json.JSONObject;


public interface Command {
    JSONObject execute(JSONObject json);
}
