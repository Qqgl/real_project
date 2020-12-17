package com.imgs.pretty.service;

import com.imgs.pretty.entity.Image;
import com.imgs.pretty.repository.ImgRepository;
import com.imgs.pretty.utils.TimeUtil;
import org.bson.types.Binary;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImgsServiceTest {

    @Autowired
    private ImgRepository imgRepository;

    @Test
    public void save(){
        Image image = new Image().setContent(new Binary("hello word".getBytes()))
                .setContentType("string")
                .setName("test")
                .setUploadTime(TimeUtil.getCurTime(LocalDateTime.now()))
                .setSize(100);
        Image save = imgRepository.save(image);
        System.out.println(save.getId());
    }

    @Test
    public void findOne(){
        Optional<Image> optional = imgRepository.findById("5fdafdf5ef8a56167c086461");
        System.out.println(optional.get());
    }

}
