package CommandPattern.userStories;

import CommandPattern.Controller.PropertiesHandler;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AppThreadPool {
    static int numberOfThreads = initializeNumberofthreads();//Integer.parseInt(PropertiesHandler.getProperty("max_app_threads"));
    private static ExecutorService executor = Executors.newFixedThreadPool( numberOfThreads);


    private static int initializeNumberofthreads(){
        Properties conf = new Properties();
        try {

            File file = new File("./src/main/java/CommandPattern/userStories/config.properties");
            FileInputStream confLoc = new FileInputStream(file);
            conf.load(confLoc);
            int number = Integer.parseInt(conf.getProperty("max_app_threads"));
            return number;
        } catch (Exception x) {
            x.printStackTrace();
            return 1;
        }
    }


    public static ExecutorService getInstance(){
        return executor;
    }

    public static void setNumberOfThreads(int x){
        PropertiesHandler.addProperty("max_app_threads", x+"");
        numberOfThreads = x;
        executor = Executors.newFixedThreadPool(x);
    }

    public static int getNumberOfThreads() {
        return numberOfThreads;
    }

    public static ExecutorService update(){
        int x = Integer.parseInt(PropertiesHandler.getProperty("max_app_threads"));
        if (numberOfThreads != x)
        {
            executor.shutdown();
            numberOfThreads = x;
            executor = Executors.newFixedThreadPool(x);
        }
        return executor;
    }


}