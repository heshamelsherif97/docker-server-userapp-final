import CommandPattern.Controller.Controller;
import CommandPattern.Controller.PropertiesHandler;
import CommandPattern.userStories.UserApp;
import NettyHTTP.NettyHTTPServer;

public class Microservice {


    public static void main(String[] args) {
        UserApp x = UserApp.getInstance();
        PropertiesHandler.loadPropertiesHandler();
        x.main(args);

        Controller r = Controller.getInstance();
        try {
            r.main(args);
         }catch (Exception e){
            e.printStackTrace();
        }


    }
}