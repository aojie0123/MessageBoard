package com.imooc.dao.impl;

import com.imooc.dao.MessageDao;
import com.imooc.domain.Message;
import com.imooc.domain.User;
import com.imooc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao {
    @Override
    public List<Message> findMyMessage(User loginUser) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Message> list = new ArrayList<>();
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM message WHERE user_id = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, loginUser.getUid());
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Message(rs.getInt("id"), rs.getInt("user_id"), rs.getString("username"), rs.getString("title"), rs.getString("content"), rs.getString("create_time")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstm, con);
        }
        return null;
    }

    @Override
    public List<Message> findAllMessage() {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Message> list = new ArrayList<>();
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM message ORDER BY create_time DESC";
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(new Message(rs.getInt("id"), rs.getInt("user_id"), rs.getString("username"), rs.getString("title"), rs.getString("content"), rs.getString("create_time")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstm, con);
        }
        return null;
    }

    @Override
    public Message findOne(int mid) {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT * FROM message WHERE id = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, mid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                return new Message(rs.getInt("id"), rs.getInt("user_id"), rs.getString("username"), rs.getString("title"), rs.getString("content"), rs.getString("create_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, pstm, con);
        }
        return null;
    }

    @Override
    public void update(Message message) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "UPDATE message SET title = ?, content = ? WHERE id = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1, message.getTitle());
            pstm.setString(2, message.getContent());
            pstm.setInt(3, message.getMid());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstm, con);
        }
    }

    @Override
    public void delete(int id) {
        Connection con = null;
        PreparedStatement pstm = null;
        try {
            con = JDBCUtils.getConnection();
            String sql = "DELETE FROM message WHERE id = ?";
            pstm = con.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(pstm, con);
        }
    }
}
