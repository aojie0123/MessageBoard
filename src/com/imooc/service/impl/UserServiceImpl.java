package com.imooc.service.impl;

import com.imooc.dao.UserDao;
import com.imooc.dao.impl.UserDaoImpl;
import com.imooc.domain.User;
import com.imooc.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String regist(User user) {
        UserDao userDao = new UserDaoImpl();
        //  查询用户是否存在
        if (!userDao.isRegisted(user)) {
            System.out.println("没有该用户");
            userDao.regist(user);
        }
        return userDao.regist(user);
    }
}
