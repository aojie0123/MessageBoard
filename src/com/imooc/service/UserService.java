package com.imooc.service;

import com.imooc.domain.User;

public interface UserService {
    String regist(User user);

    User login(User user);
}
