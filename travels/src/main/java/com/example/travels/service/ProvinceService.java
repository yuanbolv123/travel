package com.example.travels.service;

import com.example.travels.entity.Province;

import java.util.List;

public interface ProvinceService {

    //当前页   每页显示等记录数
    List<Province> findByPage(Integer page,Integer rows);

    //查询总条数
    Integer findTotals();

    //插入一个景点
    void save(Province province);

    //删除省份
    void delect(String id);


    //查询一个省份
    Province findOne(String id);
    //修改省份信息
    void update(Province province);
}
