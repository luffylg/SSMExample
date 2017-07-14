package com.unionpay.taskmonitor.dao;

import com.unionpay.taskmonitor.bean.SpiderStatus;

import java.sql.*;

/**
 * Created by luffylg on 2017/6/29.
 */
public class SpiderStatusDao extends DaoBase {
    public SpiderStatusDao() throws ClassNotFoundException, SQLException {
    }

    public SpiderStatus QuerySpiderStatus() throws SQLException {
        String sqlstring = "SELECT * FROM `spiderstatus` LIMIT 1";
        StringBuilder sql=new StringBuilder(sqlstring);
        PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            SpiderStatus status = new SpiderStatus();
            status.setPosition(rs.getInt("position"));
            status.setIsalive(rs.getInt("isalive"));
            return status;
        }
        rs.close();
        preparedStatement.close();
        return null;
    }
}
