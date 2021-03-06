package com.atshixin.ucenter.service;

import com.atshixin.ucenter.dto.LoginDto;
import com.atshixin.ucenter.dto.RegisterDto;
import com.atshixin.ucenter.entity.Member;
import com.atshixin.ucenter.vo.LoginVo;
import com.atshixin.ucenter.vo.RegisterVo;
import com.atshixin.util.R;
import com.atshixin.util.TokenR;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-02-22
 */
public interface MemberService extends IService<Member> {
    TokenR login(LoginDto loginDto);

    void register(RegisterDto registerDto);
    Integer countRegisterDay(String day);
}
