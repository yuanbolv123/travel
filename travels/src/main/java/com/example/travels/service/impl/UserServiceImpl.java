package com.example.travels.service.impl;

import com.example.travels.dao.UserDAO;
import com.example.travels.entity.User;
import com.example.travels.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(User user) {
        if(userDAO.findByUsername(user.getUsername())==null){
            userDAO.save(user);
        }else {
            throw new RuntimeException("用户名已存在");
        }

    }

    @Override
    public User login(User user) {
        User userDB = userDAO.findByUsername(user.getUsername());
        if(userDB!=null){
            if(user.getPassword().equalsIgnoreCase(userDB.getPassword())){
                return userDB;
            }
            throw new RuntimeException("密码输入错误");
        }else {
            throw new RuntimeException("用户名不存在");
        }

    }
}
