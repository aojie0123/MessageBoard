package com.imooc.dao;

import com.imooc.domain.User;

public interface UserDao {
    String regist(User user);

    boolean isRegisted(User user);
}
