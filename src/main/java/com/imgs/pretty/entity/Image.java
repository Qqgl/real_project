package com.imgs.pretty.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Accessors(chain = true)
public class Image {

    @Id
    private String id;

    private String name;

    private String uploadTime;

    private Binary content;

    private String contentType;

    private long size;
}
