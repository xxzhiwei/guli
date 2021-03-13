package com.atshixin.ucenter.service;

import com.atshixin.ucenter.entity.Member;

public interface WeChatService {
    Member getUserByOpenId(String openId);
}
