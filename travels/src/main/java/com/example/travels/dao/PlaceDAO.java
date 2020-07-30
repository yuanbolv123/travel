package com.example.travels.dao;
import com.example.travels.entity.Place;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlaceDAO extends BaseDAO<Place,String>{
    //根据省份id查景点列表
   List<Place> findByProvinceIdPage(@Param("start") Integer start,@Param("rows") Integer rows,@Param("provinceId") String provinced);

    Integer findByProvinceIdCounts(String provinceId);

}
