package com.atshixin.ucenter.controller;

import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.ucenter.dto.LoginDto;
import com.atshixin.ucenter.dto.RegisterDto;
import com.atshixin.ucenter.entity.Member;
import com.atshixin.ucenter.service.MemberService;
import com.atshixin.util.JWT;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.atshixin.util.TokenR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public R getUserInfo(HttpServletRequest request) {
        String id = JWT.getUserIdByToken(request);
        Member member = memberService.getById(id);
        return ResultHelper.format(member);
    }

    @GetMapping("/{id}")
    public R getUserInfo(@PathVariable("id") String userId) {
        Member user = memberService.getById(userId);
        return ResultHelper.format(user);
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginDto loginDto) {
        TokenR tokenR = memberService.login(loginDto);
        return ResultHelper.format(tokenR);
    }

    @PostMapping("/register")
    public R register(@RequestBody RegisterDto registerDto) {
        memberService.register(registerDto);
        return R.ok();
    }
}

