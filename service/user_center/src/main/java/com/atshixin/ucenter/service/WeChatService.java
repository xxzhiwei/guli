package com.atshixin.ucenter.service;

import com.atshixin.util.R;

public interface WeChatService {
    R login();

    // 获取微信code
    void getCode();

    // 通过微信code获取信息（openid，access_token, refresh_token等
    void getInfoByCode();

    void getUserInfo();
}
