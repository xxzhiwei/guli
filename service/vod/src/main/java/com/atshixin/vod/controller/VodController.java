package com.atshixin.vod.controller;

import com.atshixin.util.R;
import com.atshixin.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/vod")
@CrossOrigin
public class VodController {

    @Autowired
    VodService vodService;

    @PostMapping("/videos")
    public R uploadVideo(@RequestParam("file") MultipartFile file) {
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId", videoId);
    }
}
