package com.example.travels.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDAO <T,K>{
    void save(T t);

    void update(T t);

    void delete(K k);

    T findOne(K k);

    List<T> findAll();
   //分页
    List<T> findByPage(@Param("start") Integer start, @Param("rows") Integer rows);
   //总条数
    Integer findTotals();


}
