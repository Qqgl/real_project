package com.imgs.pretty.utils;

import org.springframework.util.StringUtils;

import java.util.Objects;

public class CommonUtil {

    public static boolean isNullOrEmpty(String s){
        return Objects.isNull(s) || StringUtils.isEmpty(s.trim());
    }

    public static void main(String[] args) {
        String s = " ";
        System.out.println(isNullOrEmpty(s));
    }
}
