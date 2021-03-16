package com.atshixin.vod.controller;

import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.util.R;
import com.atshixin.util.ResultCode;
import com.atshixin.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vod")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("/videos")
    public R uploadVideo(@RequestParam("file") MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId", videoId);
    }

    @DeleteMapping("/videos/{id}")
    public R deleteVideoById(@PathVariable("id") String videoId) {
        if (StringUtils.isEmpty(videoId)) {
            throw new GuliException(ResultCode.ERROR, "视频id不能为空");
        }
        vodService.deleteVideoById(videoId);
        return R.ok();
    }

    @DeleteMapping("/videos")
    public R deleteVideosByIds(@RequestParam("videoIds") List<String> videoIds) {
        if (videoIds.isEmpty()) {
            throw new GuliException(ResultCode.ERROR, "视频id不能为空");
        }
        vodService.deleteVideosByIds(videoIds);
        return R.ok();
    }

    @GetMapping("/videos/{id}/playAuth")
    public R getVideoPlayAuthById(@PathVariable("id") String videoSourceId) {
        String playAuth = vodService.getVideoPlayAuthById(videoSourceId);
        return R.ok().data("playAuth", playAuth);
    }
}
