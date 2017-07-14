package com.unionpay.taskmonitor.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by luffylg on 2017/7/3.
 */
public class PropertiesHandle {
    public Properties handleProperty(String pathname) {
        Properties properties =new Properties();
        String path = Thread.currentThread().getContextClassLoader().getResource(pathname).getPath();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
