package com.atshixin.ucenter.service.impl;

import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.ucenter.dto.LoginDto;
import com.atshixin.ucenter.dto.RegisterDto;
import com.atshixin.ucenter.entity.Member;
import com.atshixin.ucenter.mapper.MemberMapper;
import com.atshixin.ucenter.service.MemberService;
import com.atshixin.util.JWT;
import com.atshixin.util.MD5;
import com.atshixin.util.TokenR;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-02-22
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public TokenR login(LoginDto loginDto) {

        String phone = loginDto.getMobile();
        String password = loginDto.getPassword();

        // 1. 非空校验
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001, "登录失败，手机号或密码不能为空");
        }

        // 2. 校验手机号是否正确
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", phone);

        Member member = baseMapper.selectOne(queryWrapper);

        if (member == null) {
            throw new GuliException(20001, "登录失败，该手机号尚未注册");
        }

        // 3. 校验密码
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new GuliException(20001, "登录失败，手机号或密码错误");
        }

        // 4. 用户是否被禁用
        if (member.getIsDisabled()) {
            throw new GuliException(20001, "登录失败，该用户已被禁用");
        }

        return JWT.generateToken(member.getId(), member.getNickname());
    }

    @Override
    public void register(RegisterDto registerDto) {

        String nickname = registerDto.getNickname();
        String password = registerDto.getPassword();
        String code = registerDto.getCode();
        String mobile = registerDto.getMobile();

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("mobile", mobile);

        Member member = baseMapper.selectOne(queryWrapper);

        if (!ObjectUtils.isEmpty(member)) {
            throw new GuliException(20001, "该手机号已注册");
        }

        if (!code.equals("123456")) {
            throw new GuliException(20001, "验证码错误");
        }

        Member newMember = new Member();
        newMember.setNickname(nickname);
        newMember.setPassword(MD5.encrypt(password));
        newMember.setMobile(mobile);

        baseMapper.insert(newMember);
    }
}
