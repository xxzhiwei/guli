package com.atshixin.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "guli-order")
@Component
public interface OrderFeignClient {
    @GetMapping("/orders/{memberId}/{orderNo}/status")
    boolean isPayOrder(@PathVariable String memberId, @PathVariable String orderNo);
}
