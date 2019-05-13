package com.imooc.service;

import com.imooc.domain.Message;
import com.imooc.domain.User;

import java.util.List;

public interface MessageService {
    List<Message> findMyMessage(User loginUser);

    List<Message> findAllMessage();

    Message findOne(int mid);
}
