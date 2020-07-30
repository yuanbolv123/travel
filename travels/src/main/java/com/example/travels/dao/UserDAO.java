package com.example.travels.dao;

import com.example.travels.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    void save(User user);
    User findByUsername(String username);
}
