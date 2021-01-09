package com.imgs.pretty.repository;

import com.imgs.pretty.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgRepository extends MongoRepository<Image,String> {

    List<Image> findByIdIn(List<String>ids);
}
