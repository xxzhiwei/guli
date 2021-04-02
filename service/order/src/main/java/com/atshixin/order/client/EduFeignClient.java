package com.atshixin.order.client;

import com.atshixin.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "guli-edu")
public interface EduFeignClient {
    @GetMapping("/edu/courses/{id}")
    R getCourseById(@PathVariable("id") String courseId,  @RequestParam String callType);
}
