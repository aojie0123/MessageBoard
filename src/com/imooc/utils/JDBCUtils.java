package com.imooc.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils {

    public static final ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * 获得连接池
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

    /**
     * 获得连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 释放资源
     * @param rs
     * @param pstm
     * @param con
     */
    public static void release(ResultSet rs, PreparedStatement pstm, Connection con) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }

        if (pstm != null) {
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            pstm = null;
        }

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            con = null;
        }
    }

}
