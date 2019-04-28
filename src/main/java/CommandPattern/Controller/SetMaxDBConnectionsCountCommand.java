package CommandPattern.Controller;

import CommandPattern.Command;
import CommandPattern.DBConnection;
import org.json.JSONObject;

public class SetMaxDBConnectionsCountCommand implements Command {

    public JSONObject execute(JSONObject json) {

        int x = Integer.parseInt(json.getString("db_conn"));
        DBConnection.setMaxNumber(x);

        return new JSONObject();
    }
}
