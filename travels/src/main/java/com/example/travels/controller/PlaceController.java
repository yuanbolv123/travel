package com.example.travels.controller;


import com.example.travels.entity.Place;
import com.example.travels.entity.Result;
import com.example.travels.service.PlaceService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController//前后端分离
@CrossOrigin//跨域
@RequestMapping("place")

public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @Value("${upload.dir}")
    private String realPath;

    /**
     * 根据省份id查询景点的方法
     */
    @GetMapping("findAllPlace")
    public Map<String, Object> findAllPlace(Integer page, Integer rows, String provinceId) {
        HashMap<String, Object> map = new HashMap<>();
        page=page==null?1:page;
        rows=rows==null?3:rows;
        //景点集合
        List<Place> places = placeService.findByProvinceIdPage(page, rows, provinceId);

        //处理分页
        Integer counts = placeService.findByProvinceIdCounts(provinceId);
        //总页数
        Integer totalPage = counts % rows == 0 ? (counts / rows) : (counts / rows + 1);
        map.put("places", places);
        map.put("page", page);
        map.put("counts", counts);
        map.put("totalPage", totalPage);
        return map;
    }

    /**
     * 根据id删除景点
     */
    @GetMapping("delete")
    public Result delete(String id){
        Result result = new Result();
        try{
            placeService.delete(id);
            result.setMsg("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(false);
            result.setMsg("网络繁忙中");
        }
        return result;
    }


    //根据id查看景点信息
    @GetMapping("findOne")
    public Place findOne(String id){
        return placeService.findOne(id);
    }


    //修改
    @PostMapping("update")
    public Result update(MultipartFile pic, Place place) throws Exception{
        Result result = new Result();
        try{

            //对接收文件做base64
            String picpath = Base64Utils.encodeToString(pic.getBytes());
            place.setPicpath(picpath);
            //处理文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            pic.transferTo(new File(realPath,newFileName));

            //修改景点信息
            placeService.update(place);
            result.setMsg("修改景点信息成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(false);
            result.setMsg("修改景点信息失败");
        }



        return result;
    }




    //插入一个景点
    @PostMapping("save")
    public  Result save(MultipartFile pic, Place place)throws Exception{
        Result result = new Result();
       //System.out.println(pic.getOriginalFilename());
        try{
            //文件上传
            String extension = FilenameUtils.getExtension(pic.getOriginalFilename());
            String newFileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + extension;
            //base64编码处理
            place.setPicpath(Base64Utils.encodeToString(pic.getBytes()));
            //文件上传
            File file = new File(realPath);
            pic.transferTo(new File(file,newFileName));
            //保存place对象
            placeService.save(place);
            result.setMsg("保存景点信息成功!!!");

       }catch (Exception e){
           e.printStackTrace();
           result.setState(false);
           result.setMsg("添加景点失败");
       }

        return result;
    }
}
