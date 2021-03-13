package com.atshixin.ucenter.service.impl;

import com.atshixin.ucenter.entity.Member;
import com.atshixin.ucenter.mapper.MemberMapper;
import com.atshixin.ucenter.service.WeChatService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
    public Member getUserByOpenId(String openId) {

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("openid", openId);

        return getOne(queryWrapper);
    }
}
