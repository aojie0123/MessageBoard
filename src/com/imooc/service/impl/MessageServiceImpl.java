package com.imooc.service.impl;

import com.imooc.dao.MessageDao;
import com.imooc.dao.impl.MessageDaoImpl;
import com.imooc.domain.Message;
import com.imooc.domain.User;
import com.imooc.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {
    @Override
    public List<Message> findMyMessage(User loginUser) {
        MessageDao messageDao = new MessageDaoImpl();
        return messageDao.findMyMessage(loginUser);
    }

    @Override
    public List<Message> findAllMessage() {
        MessageDao messageDao = new MessageDaoImpl();
        return messageDao.findAllMessage();
    }

    @Override
    public Message findOne(int mid) {
        MessageDao messageDao = new MessageDaoImpl();
        return messageDao.findOne(mid);
    }
}
