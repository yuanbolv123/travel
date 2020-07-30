package com.example.travels.service.impl;

import com.example.travels.dao.PlaceDAO;
import com.example.travels.entity.Place;
import com.example.travels.entity.Province;
import com.example.travels.service.PlaceService;
import com.example.travels.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//事务
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceDAO placeDAO;
    @Autowired
    private ProvinceService provinceService;

    @Override
    public List<Place> findByProvinceIdPage(Integer page, Integer rows, String provinceId) {
        int start=(page-1) *rows;
        return placeDAO.findByProvinceIdPage(start,rows,provinceId);
    }

    @Override
    public Integer findByProvinceIdCounts(String provinceId) {
        return placeDAO.findByProvinceIdCounts(provinceId);
    }

    @Override
    public void delete(String id) {
        placeDAO.delete(id);
    }

    @Override
    public Place findOne(String id) {
        return placeDAO.findOne(id);
    }

    @Override
    public void save(Place place) {
        //保存景点
        placeDAO.save(place);
        //查询原始省份信息
        Province province = provinceService.findOne(place.getProvinceid());
        //更新景点个数
        province.setPlacecounts(province.getPlacecounts()+1);
        provinceService.update(province);
    }

    @Override
    public void update(Place place) {
        placeDAO.update(place);
    }
}
