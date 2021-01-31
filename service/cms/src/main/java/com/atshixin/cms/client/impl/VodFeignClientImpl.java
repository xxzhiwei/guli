package com.atshixin.cms.client.impl;

import com.atshixin.cms.client.VodFeignClient;
import com.atshixin.util.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFeignClientImpl implements VodFeignClient {
    @Override
    public R deleteVideoById(String videoId) {
        return R.error().message("删除视频出错，请检查服务是否可用；");
    }

    @Override
    public R deleteVideosByIds(List<String> videoIds) {
        return R.error().message("批量删除视频出错，请检查服务是否可用；");
    }
}
