package com.unionpay.taskmonitor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by luffylg on 2017/6/30.
 */
public class IpConnectionDao extends DaoBase {
    private long expiretime = Long.parseLong(properties.getProperty("expiretime"));

    public IpConnectionDao() throws ClassNotFoundException, SQLException {
    }

    public int queryIpConnection(String ip) throws SQLException {
        String sqlstring = "SELECT * FROM `ipconnection` WHERE 1=1";
        StringBuilder sql = new StringBuilder(sqlstring);
        sql.append(" and ip = '" + ip + "'");
        long ctime = System.currentTimeMillis() - expiretime;
        Timestamp timestamp = new Timestamp(ctime);
        sql.append("and `lastReqTime` > '" + timestamp.toString() + "'");
        PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
        ResultSet rs = preparedStatement.executeQuery();
        int count = 0;
        while (rs.next()) {
            count++;
        }
        rs.close();
        preparedStatement.close();
        return count;
    }

    public void updateIpConnection(String ip, String macadress) throws SQLException {
        String sqlstring = "UPDATE `ipconnection` SET `ip` = '" + ip + "', `macAdress` = '" + macadress
                + "' ,lastReqTime = CURRENT_TIMESTAMP() WHERE 1=1";
        StringBuilder sql = new StringBuilder(sqlstring);
        sql.append(" and ip = '" + ip + "'");
        sql.append(" and macAdress = '" + macadress + "'");
        PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
        int rs = preparedStatement.executeUpdate();
        preparedStatement.close();
        if (rs == 0) {
            insertIpConnection(ip, macadress);
        }
    }

    private void insertIpConnection(String ip, String macadress) throws SQLException {
        String sqlstring = "INSERT INTO `ipconnection` (`ip`, `macAdress`) VALUES ('" + ip + "', '" + macadress + "')";
        StringBuilder sql = new StringBuilder(sqlstring);
        PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
        boolean rs = preparedStatement.execute();
        preparedStatement.close();
    }

    public void cleantable() throws SQLException {
        // 清除过期的记录
        long ctime = System.currentTimeMillis() - expiretime;
        Timestamp timestamp = new Timestamp(ctime);
        String sqlstring = "DELETE FROM `ipconnection` WHERE `lastReqTime` < '" + timestamp.toString() + "'";
        StringBuilder sql = new StringBuilder(sqlstring);
        PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
        boolean rs = preparedStatement.execute();
        preparedStatement.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new IpConnectionDao().cleantable();
    }
}
