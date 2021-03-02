package com.atshixin.ucenter.service.impl;

import com.atshixin.ucenter.entity.Member;
import com.atshixin.ucenter.mapper.MemberMapper;
import com.atshixin.ucenter.service.WeChatService;
import com.atshixin.util.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-02-22
 */
@Service
public class WeChatServiceImpl extends ServiceImpl<MemberMapper, Member> implements WeChatService {

    @Override
    public R login() {
        return null;
    }

    @Override
    public void getCode() {

        Map<String, String> codeParams = new HashMap<>();
        // appid, redirect_uri, response_type, scope
        codeParams.put("appid", "");
        codeParams.put("redirect_uri", "");
        codeParams.put("response_type", "code");
        codeParams.put("scope", "snsapi_login");
    }

    @Override
    public void getInfoByCode() {

    }

    @Override
    public void getUserInfo() {

    }
}
