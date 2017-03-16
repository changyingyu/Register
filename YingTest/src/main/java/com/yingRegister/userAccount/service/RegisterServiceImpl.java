package com.yingRegister.userAccount.service;

import com.yingRegister.userAccount.model.User;
import com.yingRegister.userAccount.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void insertUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        registerRepository.save(user);
    }

    @Override
    public User checkExistUser(String username) {
        return registerRepository.findByUsername(username);
    }
}
