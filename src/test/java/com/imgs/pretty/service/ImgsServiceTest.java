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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImgsServiceTest {

    private static ExecutorService pool = Executors.newCachedThreadPool();

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    @Autowired
    private ImgRepository imgRepository;

    @Test
    public void save(){
        Image image = new Image().setContent(new Binary("hello word2".getBytes()))
                .setContentType("jpg")
                .setName("test2")
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

    @Test
    public void findByIds(){
        List<Image> imageList = imgRepository.findByIdIn(Arrays.asList("5fdc658bef8a5628f827381f", "5fdafdf5ef8a56167c086461"));
        System.out.println(imageList);
    }

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 10; i++) {

            pool.execute(()->{
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Optional<Image> optional = imgRepository.findById("5fdafdf5ef8a56167c086461");
                System.out.println(optional.get());
            });
        }

        Thread.sleep(2_000);
    }

}
