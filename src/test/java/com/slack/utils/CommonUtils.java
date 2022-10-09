package com.slack.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CommonUtils {

    public static String readProp(String key) {
        File propFile = new File("src/test/resources/application.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(propFile));
        } catch (IOException ex) {
            throw new RuntimeException();
        }
        return properties.getProperty(key);
    }


}
