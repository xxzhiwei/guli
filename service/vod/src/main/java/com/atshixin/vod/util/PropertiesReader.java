package com.atshixin.vod.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Getter
@Component
public class PropertiesReader {

    @Value("${ali-oss-access-keyid}")
    private String assessKeyId;

    @Value("${ali-oss-access-keysecret}")
    private String assessSecret;
}