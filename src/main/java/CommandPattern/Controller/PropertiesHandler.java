package CommandPattern.Controller;

import CommandPattern.Command;
import IPGetter.IPGetter;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesHandler implements Command {
    static Properties prop = new Properties();
    static OutputStream output;
    static InputStream input;

    public static void loadPropertiesHandler(){
        IPGetter x = new IPGetter();
        try {

            output = new FileOutputStream("./src/main/java/CommandPattern/userStories/config.properties");
            input = new FileInputStream("./src/main/java/CommandPattern/userStories/config.properties");

            // set the properties value
            prop.setProperty("max_db_threads", "20");
            prop.setProperty("max_app_threads", "20");
            prop.setProperty("freeze", "false");
            prop.setProperty("restart", "false");
            prop.setProperty("Db_URL2","192.168.0.111");
            prop.setProperty("UsersApp","35.188.200.142");
            prop.setProperty("UsersPort","80");
            prop.setProperty("Db_URL", "jdbc:postgresql://192.168.0.111:5432/");
            prop.setProperty("nettyPort","8083");
            //prop.setProperty("nettyHttp", x.getPublicIP());
            prop.setProperty("JedisIP", "172.17.0.5:7000");
            /*
            Db_URL = jdbc:postgresql://192.168.0.111:5432/
            UsersApp = localhost
            Db_URL2 = 192.168.0.111
                */
            prop.store(output, null);
        } catch (Exception e) {
        }
    }

    public JSONObject execute(JSONObject o){
        String key = o.getString("key");
        String value = o.getString("value");
        return addProperty(key, value);
    }

    public static JSONObject addProperty(String key, String val) {
        JSONObject result ;
        try {
            input = new FileInputStream("./src/main/java/CommandPattern/userStories/config.properties");
            output = new FileOutputStream("./src/main/java/CommandPattern/userStories/config.properties");
            prop.load(input);
            prop.setProperty(key, val);
            System.out.println("in addProperty");
            prop.store(output, null);
            result = new JSONObject("{ \"message\" : \"Added Property\" }");
        } catch(Exception e) {
            System.out.println("error in addProperty");
            result=  new JSONObject("{ \"message\" : \"Error in adding property\" }");
        }
        return result;
    }

    public static String getProperty(String key) {
        try{
            input = new FileInputStream("./src/main/java/CommandPattern/userStories/config.properties");
            prop.load(input);
            return prop.getProperty(key);
        } catch(Exception e) {
            return "error1";
        }
    }
}
