package com.atshixin.ucenter.controller;

import com.atshixin.ucenter.service.WeChatService;
import com.atshixin.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/ucenter/WeChat")
public class WeChatController {

    @Autowired
    private WeChatService weChatService;

    @GetMapping
    public R login() {
        weChatService.login();
        return R.ok();
    }
}
