package com.imgs.pretty.controller;

import com.imgs.pretty.common.Result;
import com.imgs.pretty.service.ImgsService;
import com.imgs.pretty.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * 预处理图片相关的处理器
 */
@RestController
@RequestMapping(value = "/img/")
public class ImgsController {

    @Autowired
    private ImgsService imgsService;

    /**
     * 上传图片文件
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "upload")
    public String preHandImg(@RequestParam(name = "file",required = false) MultipartFile file, HttpServletRequest request){
        if (Objects.isNull(file)){
            return Result.returnParamError();
        }
        if (file.getSize() > 10*1024*1024){
            return Result.returnFail(100,"文件大小不能超过10M");
        }

        return imgsService.handUploadImg(file);
    }

    /**
     * 图片文件预览或下载
     * @return
     */
    @RequestMapping(value = "preShowOrDownloadImg/{id}")
    public String preShowOrDownloadImg(@PathVariable String id, HttpServletResponse response){
        if (CommonUtil.isNullOrEmpty(id)) {
            return Result.returnParamError();
        }
        return imgsService.preShowOrDownloadImg(id);
    }
}
