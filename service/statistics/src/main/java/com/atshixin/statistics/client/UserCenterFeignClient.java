package com.atshixin.statistics.client;

import com.atshixin.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("guli-ucenter")
public interface UserCenterFeignClient {
    //查询某一天注册人数
    @GetMapping("/ucenter/member/countRegister/{day}")
    R countRegister(@PathVariable("day") String day);
}
