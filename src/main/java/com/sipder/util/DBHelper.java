package com.sipder.util;

import java.sql.*;
import java.util.List;

public class DBHelper {
	//加载驱动，driverurl和用户名，密码
    public static final String driver_class = "com.mysql.cj.jdbc.Driver";
    public static final String driver_url = "jdbc:mysql://localhost:3306/sys?useSSL=false&allowPublicKeyRetrieval=true&useunicode=true&characterEncoding=utf8";
    public static final String user = "root";
    public static final String password = "wnwn543321105";
    private static Connection conn = null;
    private PreparedStatement pst = null;
    private ResultSet rst = null;

    /**

     * Connection

     */

    public DBHelper() {
        try {
            conn = DBHelper.getConnInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static synchronized Connection getConnInstance() {
        if (conn == null) {
            try {
                Class.forName(driver_class);
                conn = DriverManager.getConnection(driver_url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("连接数据库成功");
        }
        return conn;
    }

    public void close() {
        try {
            if (conn != null) {
                DBHelper.conn.close();
            }
            if (pst != null) {
                this.pst.close();
            }
            if (rst != null) {
                this.rst.close();
            }
            System.out.println("关闭数据库成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ResultSet executeQuery(String sql, List<String> sqlValues) {
        try {
            pst = conn.prepareStatement(sql);
            if (sqlValues != null && sqlValues.size() > 0) {
                setSqlValues(pst, sqlValues);
            }
            rst = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rst;

    }
    

    public int executeUpdate(String sql, List<String> sqlValues) {
        int result = -1;
        try {
            pst = conn.prepareStatement(sql);
            if (sqlValues != null && sqlValues.size() > 0) {
                setSqlValues(pst, sqlValues);
            }
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

 

    private void setSqlValues(PreparedStatement pst, List<String> sqlValues) {
        for (int i = 0; i < sqlValues.size(); i++) {
            try {
                pst.setObject(i + 1, sqlValues.get(i));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
/*
 * 
 * mysql> show variables like '%time_zone%';
 * mysql>set global time_zone='+8:00';
 * mysql>flush privileges; #刷新
 */



