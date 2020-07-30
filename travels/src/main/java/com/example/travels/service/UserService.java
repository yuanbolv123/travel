package com.example.travels.service;

import com.example.travels.entity.User;

public interface UserService {
    void register(User user);

    User login(User user);


}
