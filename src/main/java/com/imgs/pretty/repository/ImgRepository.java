package com.imgs.pretty.repository;

import com.imgs.pretty.entity.Image;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgRepository extends MongoRepository<Image,String> {
}
