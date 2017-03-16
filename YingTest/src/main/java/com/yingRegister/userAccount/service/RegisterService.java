package com.yingRegister.userAccount.service;

import com.yingRegister.userAccount.model.User;

public interface RegisterService {
    void insertUser(User user);

    User checkExistUser(String username);
}
