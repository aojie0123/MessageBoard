package com.imooc.dao;

import com.imooc.domain.Message;
import com.imooc.domain.User;

import java.util.List;

public interface MessageDao {
    List<Message> findMyMessage(User loginUser);

    List<Message> findAllMessage();
}
