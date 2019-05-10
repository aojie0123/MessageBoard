package com.imooc.service.impl;

import com.imooc.dao.UserDao;
import com.imooc.dao.impl.UserDaoImpl;
import com.imooc.domain.User;
import com.imooc.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public String regist(User user) {
        UserDao userDao = new UserDaoImpl();
        String msg = "";
        //  查询用户是否存在
        if (!userDao.isRegisted(user)) {
            //  未曾注册
            msg = userDao.regist(user);
        } else {
            //  已注册
            msg = "registed";
        }
        return msg;
    }

    @Override
    public User login(User user) {
        UserDao userDao = new UserDaoImpl();
        return userDao.login(user);
    }
}
