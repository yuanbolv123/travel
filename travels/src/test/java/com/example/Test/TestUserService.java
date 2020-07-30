package com.example.Test;

import com.example.travels.TravelsApplication;
import com.example.travels.entity.Province;
import com.example.travels.entity.User;
import com.example.travels.service.ProvinceService;
import com.example.travels.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = TravelsApplication.class)
@RunWith(SpringRunner.class)
public class TestUserService {
    @Autowired
    private ProvinceService provinceService;
    @Test
    public void testSave(){
        List<Province> list=provinceService.findByPage(1,5);
        list.forEach(province -> {
            System.out.println(province);
        });
    }
}
