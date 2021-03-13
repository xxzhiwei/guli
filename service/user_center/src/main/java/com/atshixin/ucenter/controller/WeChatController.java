package com.atshixin.ucenter.controller;

import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.ucenter.entity.Member;
import com.atshixin.ucenter.service.MemberService;
import com.atshixin.ucenter.service.WeChatService;
import com.atshixin.ucenter.util.HttpClientUtils;
import com.atshixin.ucenter.util.WeChatPropertiesReader;
import com.atshixin.util.JWT;
import com.atshixin.util.R;
import com.atshixin.util.TokenR;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

@Controller
@CrossOrigin
@RequestMapping(value = {"/ucenter/WeChat", "/api/ucenter/wx"})
public class WeChatController {

    @Autowired
    private WeChatPropertiesReader weChatPropertiesReader;

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private MemberService memberService;

    /**
     * 生成扫描二维码
     */
    @GetMapping("/QRCode")
    public String getQRCode() {
        // 1. 重定向路径
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect"
                + "?appid=%s"
                + "&redirect_uri=%s"
                + "&response_type=code"
                + "&scope=snsapi_login"
                + "&state=%s"
                + "#wechat_redirect";

        // 2. encode重定向url
        String redirectUrl = weChatPropertiesReader.getRedirectUrl();

        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 3. 填充baseUrl占位符
        String url = String.format(baseUrl, weChatPropertiesReader.getAppId(), redirectUrl, "atshixin");

        // 4. 不能用RestController，否则只会返回字符串，不会重定向到url
        return "redirect:" + url;
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code) {
        // 1. 根据code、app-id、app-secret获取access_token
        String baseUrl = "https://api.weixin.qq.com/sns/oauth2/access_token"
                + "?appid=%s"
                + "&secret=%s"
                + "&code=%s"
                + "&grant_type=authorization_code";

        String url = String.format(baseUrl, weChatPropertiesReader.getAppId(), weChatPropertiesReader.getAppSecret(), code);
        String redirectBaseUrl = "http://localhost:3000/"
                + "?token=%s"
                + "&expiresTime=%s"
                + "&expiresDays=%s";

        try {
            // 1. 请求地址返回字符串
            String result = HttpClientUtils.get(url);
            // 2. Gson：json转换工具
            Gson gson = new Gson();
            // 3. 转换字符串为map
            HashMap resultMap = gson.fromJson(result, HashMap.class);
            // 4. 读取access_token、openid
            String accessToken = (String) resultMap.get("access_token");
            String openId = (String) resultMap.get("openid");
            Member member = weChatService.getUserByOpenId(openId);
            // 5. 查询用户，没有则新建用户（微信）
            if (member == null) {
                String userInfoBaseUrl = "https://api.weixin.qq.com/sns/userinfo"
                        + "?access_token=%s"
                        + "&openid=%s";
                String userInfoUrl = String.format(userInfoBaseUrl, accessToken, openId);
                String userInfoResult = HttpClientUtils.get(userInfoUrl);

                HashMap userInfoMap = gson.fromJson(userInfoResult, HashMap.class);
                Double sex = (Double) userInfoMap.get("sex");
                member = new Member();
                member.setNickname((String) userInfoMap.get("nickname"));
                member.setOpenid(openId);
                member.setSex(sex.intValue());
                member.setAvatar((String) userInfoMap.get("headimgurl"));
                memberService.save(member);
            }

            TokenR tokenR = JWT.generateToken(member.getId(), member.getNickname());

            return "redirect:" + String.format(redirectBaseUrl, tokenR.getToken(), tokenR.getExpiresTime(), tokenR.getExpiresDays());
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(200001, "微信回调发生错误，登录失败");
        }
    }
}
