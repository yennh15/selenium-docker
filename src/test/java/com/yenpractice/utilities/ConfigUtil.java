package com.yenpractice.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    private static final Logger log = LoggerFactory.getLogger(ConfigUtil.class);
    private static Properties properties;

    private static final String QA = "config/qa.properties";
    private static final String DEV = "config/dev.properties";

    public static void init()  {
        properties = loadConfig();


       for (String key : properties.stringPropertyNames()){
            if(System.getProperties().containsKey(key)){
                properties.setProperty(key,System.getProperty(key));
            }
       }
    }
    public static InputStream loadFile(String path){
        try {
            InputStream stream = DataLoader.getData(path);
            return stream;
        } catch(Exception e){
            log.error("Unable to read the config properties file in location {}{}", path, e);
        }
        return null;
    }

    public static Properties loadConfig() {
         Properties properties = new Properties();
        try {

            properties.load(loadFile(DEV));
            if (System.getProperty("env").equalsIgnoreCase("qa")) {

                properties.load(loadFile(QA));
            }
        } catch(Exception e){
            log.error("Unable to load the config file");
        }
        return properties;
    }

    public static String get(String key){
        log.info("This is the defect need to be fixed {}",properties.getProperty(key) );
        return properties.getProperty(key);
    }
}
