package com.atshixin.oss.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

// 1、需要注入
@Getter
@Component
public class PropertiesReader {

    @Value("${ali-oss-endpoint}")
    private String endPoint;

    @Value("${ali-oss-access-keyid}")
    private String assessKeyId;

    @Value("${ali-oss-access-keysecret}")
    private String assessSecret;

    @Value("${ali-oss-bucketname}")
    private String bucketName;

    @Value("${ali-oss-prefix}")
    private String prefix;

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String format = simpleDateFormat.format(date);
        System.out.println(format);
    }
}

// 2、不需要注入：类实例化完成后，需要初始化成员变量，当静态变量使用
//@Component
//public class PropertiesReader implements InitializingBean {
//
//    @Value("${ali-oss-endpoint}")
//    private String endPoint;
//
//    @Value("${ali-oss-access-keyid}")
//    private String assessKeyId;
//
//    @Value("${ali-oss-access-keysecret}")
//    private String assessSecret;
//
//    @Value("${ali-oss-bucketname}")
//    private String bucketName;
//
//    public static String END_POINT;
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        END_POINT = endPoint;
//        // 其他省略
//    }
//}
