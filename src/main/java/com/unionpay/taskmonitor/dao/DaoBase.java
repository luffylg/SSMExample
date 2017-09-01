package com.unionpay.taskmonitor.dao;

import com.unionpay.taskmonitor.utils.PropertiesHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by luffylg on 2017/6/29.
 */
public class DaoBase {
    public static final Logger LOGGER = LoggerFactory.getLogger(DaoBase.class);
    Properties properties = new PropertiesHandle().handleProperty("properties/config.properties");
    private String user;
    private String password;
    private String url;
    public Connection conn;
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public DaoBase() throws ClassNotFoundException, SQLException {
        user =  properties.getProperty("user");
        password = properties.getProperty("password");
        url = properties.getProperty("url");

        conn = DriverManager.getConnection(url,user,password);
    }

    public void close() throws SQLException {
        conn.close();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if (conn != null){
            conn.close();
        }
    }
}
