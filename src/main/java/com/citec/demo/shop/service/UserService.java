package com.citec.demo.shop.service;

import com.citec.demo.shop.model.entity.User;

public interface UserService {

    void save(User user);

    void login(String username, String password);

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(long id);
}
