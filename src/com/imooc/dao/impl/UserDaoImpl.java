package com.imooc.dao.impl;

import com.imooc.dao.UserDao;
import com.imooc.domain.User;
import com.imooc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {
    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public String regist(User user) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
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

    /**
     * 判断是否注册
     * @param user
     * @return
     */
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

    @Override
    public User login(User user) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            rs = pstm.executeQuery();
            if (rs.next()) {
                User user1 = new User();
                user1.setUid(rs.getInt("id"));
                user1.setUsername(rs.getString("username"));
                user1.setPassword(rs.getString("password"));
                return user1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstm, con);
        }
        return null;
    }

    @Override
    public void editInformation(User user) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "UPDATE user SET username = ?, password = ? WHERE id = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, user.getUsername());
            pstm.setString(2, user.getPassword());
            pstm.setInt(3, user.getUid());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstm, con);
        }
    }

    @Override
    public User findUser(User user) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM user WHERE id = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, user.getUid());
            rs = pstm.executeQuery();
            if (rs.next()) {
                User user1 = new User();
                user1.setUid(rs.getInt("id"));
                user1.setUsername(rs.getString("username"));
                user1.setPassword(rs.getString("password"));
                return user1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstm, con);
        }
        return null;
    }
}
