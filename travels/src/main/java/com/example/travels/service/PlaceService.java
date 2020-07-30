package com.example.travels.service;

import com.example.travels.entity.Place;


import java.util.List;

public interface PlaceService {
    //根据省份id查景点列表
    List<Place> findByProvinceIdPage(Integer page, Integer rows,  String provinceId);

    Integer findByProvinceIdCounts(String provinceId);

    void delete(String id);

    Place findOne(String id);


    void save(Place place);

    void update(Place place);
}
