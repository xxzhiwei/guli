package com.atshixin.ucenter.controller;

import com.atshixin.ucenter.entity.Member;
import com.atshixin.ucenter.service.MemberService;
import com.atshixin.ucenter.vo.LoginVo;
import com.atshixin.ucenter.vo.RegisterVo;
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
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = memberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @GetMapping("{id}")
    public R getUserInfo(@PathVariable("id") String userId) {
        Member user = memberService.getById(userId);
        return ResultHelper.format(user);
    }
}

