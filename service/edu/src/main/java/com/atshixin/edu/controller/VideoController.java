package com.atshixin.edu.controller;


import com.atshixin.edu.entity.Video;
import com.atshixin.edu.service.VideoService;
import com.atshixin.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器(章节小节)
 * </p>
 *
 * @author atshixin
 * @since 2021-01-11
 */
@RestController
@RequestMapping("/edu/videos")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping
    public R addVideo(@RequestBody Video video) {
        boolean isOK = videoService.save(video);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @PutMapping("/{id}")
    public R updateVideoById(@PathVariable String id, @RequestBody Video video) {
        if (StringUtils.isEmpty(video.getId())) {
            video.setId(id);
        }
        boolean isOK = videoService.updateById(video);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteVideoById(@PathVariable(value = "id") String videoId) {
        boolean isOK = videoService.removeById(videoId);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }
}

