package com.atshixin.ucenter.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class WeChatPropertiesReader {
    @Value("${wechat.open.app-id}")
    private String appId;

    @Value("${wechat.open.app-secret}")
    private String appSecret;

    @Value("${wechat.open.redirect-url}")
    private String redirectUrl;
}
