package com.atshixin.ucenter.controller;

import com.atshixin.ucenter.dto.LoginDto;
import com.atshixin.ucenter.dto.RegisterDto;
import com.atshixin.ucenter.entity.Member;
import com.atshixin.ucenter.service.MemberService;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public R getUserInfo(@PathVariable("id") String userId) {
        Member user = memberService.getById(userId);
        return ResultHelper.format(user);
    }

    @PostMapping("/login")
    public R login(@RequestBody LoginDto loginDto) {
        String token = memberService.login(loginDto);
        return R.ok().data("token", token);
    }

    @PostMapping("/register")
    public R register(@RequestBody RegisterDto registerDto) {
        memberService.register(registerDto);
        return R.ok();
    }
}

