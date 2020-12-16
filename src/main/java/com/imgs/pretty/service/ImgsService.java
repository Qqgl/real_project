package com.imgs.pretty.service;

import com.alibaba.fastjson.JSONObject;
import com.imgs.pretty.common.Result;
import com.imgs.pretty.entity.Image;
import com.imgs.pretty.repository.ImgRepository;
import com.imgs.pretty.utils.TimeUtil;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Objects;


@Service
public class ImgsService {

    @Value("${img.format}")
    private String IMG_FORMAT;

    @Autowired
    private ImgRepository imgRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public String handUploadImg(MultipartFile file){
        JSONObject response = new JSONObject();
        try {
            //获取文件后缀
            String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            if (!IMG_FORMAT.toLowerCase().contains(suffix.toLowerCase())){
                return Result.returnFail(101,String.format("图片格式仅支持 %s 格式！",IMG_FORMAT));
            }

            //TODO 根据获取的图片，调用图片算法，获取新的图片文件，并返回给前端展示
            //1. 这里可以直接进行远程方法调用
            //2. 根据文件文件获取字节流，再通过socket发送到远程进行调用，然后获取最新的图片流文件
            //3. 将图片文件保存于图片服务器中，文件处理完成之后返回文件的ID
            //这里需要判断用户是否登录，如果用户没有登录则直接将结果文件返回，否则将最新的图片文件保存到数据库/图片服务器中，方便用于多次进行下载,再将其返回。
            Image image = new Image()
                    .setContent(new Binary(file.getBytes()))
                    .setContentType(file.getContentType())
                    .setSize(file.getSize())
                    .setName(file.getOriginalFilename())
                    .setUploadTime(TimeUtil.getCurTime(LocalDateTime.now()));
            Image img = imgRepository.save(image);

            response.put("id",img.getId());
        }catch (Exception e){

        }



        return Result.returnOK(response);
    }
}
