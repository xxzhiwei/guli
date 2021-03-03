package com.atshixin.ucenter.service;

import com.atshixin.ucenter.entity.Member;
import com.atshixin.util.R;

public interface WeChatService {
    R login();

    Member getUserByOpenId(String openId);
}
