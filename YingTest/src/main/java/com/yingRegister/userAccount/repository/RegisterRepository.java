package com.yingRegister.userAccount.repository;

import com.yingRegister.userAccount.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
