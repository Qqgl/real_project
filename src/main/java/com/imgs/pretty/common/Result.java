package com.imgs.pretty.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 所有controller返回统一数据格式
 * @param <T>
 */
@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    @JSONCreator
    public Result(){

    }

    @JSONCreator
    public Result(@JSONField int code,@JSONField String msg,@JSONField T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 正常响应码
     */
    private static final int SUCCESS_CODE = 0;

    /**
     * 正常响应消息
     */
    private static final String SUCCESS_MSG = "SUCCESS";

    /**
     * 错误码
     */
    private int code = SUCCESS_CODE;

    /**
     * 错误信息
     */
    private String msg = SUCCESS_MSG;

    /**
     * 响应内容，默认为null
     */
    private T data = null;

    /**
     * 无 data正常返回
     * @return
     */
    public static String returnOK(){
        return JSONObject.toJSONString(new Result());
    }

    /**
     * 有data的返回
     * @param data
     * @return
     */
    public static <T>String returnOK(T data){
        return JSONObject.toJSONString(new Result<>().setData(data), SerializerFeature.WriteMapNullValue);
    }

    /**
     * 失败返回，无data的情况
     * @param code
     * @param msg
     * @return
     */
    public static String returnFail(int code, String msg){
        return JSONObject.toJSONString(new Result().setCode(code).setMsg(msg),SerializerFeature.WriteNonStringKeyAsString);
    }

    public static String returnParamError(){
        return JSONObject.toJSONString(new Result<>().setCode(BaseEnumError.PARAM_ERROR.getCode()).setMsg(BaseEnumError.PARAM_ERROR.getMsg()));
    }

    /**
     * 失败返回，有data的情况
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> returnFail(int code,String msg,T data){
        return new Result<T>().setCode(code).setMsg(msg).setData(data);
    }

    public static String returnLoginFail(){
        return JSONObject.toJSONString(new Result<>().setCode(BaseEnumError.LOGIN_ERROR.getCode()).setMsg(BaseEnumError.LOGIN_ERROR.getMsg())
                    ,SerializerFeature.WriteNonStringKeyAsString);
    }

}
