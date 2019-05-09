package com.imooc.dao.impl;

import com.imooc.dao.UserDao;
import com.imooc.domain.User;
import com.imooc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    @Override
    public String regist(User user) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
            pstm = con.prepareStatement(sql);
            int i = pstm.executeUpdate();
            if (i > 0) {
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstm, con);
        }
        return "failed";
    }

    @Override
    public boolean isRegisted(User user) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE username = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, user.getUsername());
            rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstm, con);
        }
        return false;
    }
}
