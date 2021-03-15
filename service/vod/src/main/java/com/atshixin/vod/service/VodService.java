package com.atshixin.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface VodService {
    String uploadVideo(MultipartFile file);
    void deleteVideosByIds(List<String> videoIds);
    void deleteVideoById(String videoId);
    String getVideoPlayAuthById(String videoSourceId);
}
