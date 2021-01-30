package com.atshixin.edu.client;

import com.atshixin.edu.client.impl.VodFeignClientImpl;
import com.atshixin.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "guli-vod", fallback = VodFeignClientImpl.class)
@Component
public interface VodFeignClient {

    @DeleteMapping("/vod/videos/{id}")
    R deleteVideoById(@PathVariable("id") String videoId);

    @DeleteMapping("/vod/videos")
    R deleteVideosByIds(@RequestParam("videoIds") List<String> videoIds);
}
