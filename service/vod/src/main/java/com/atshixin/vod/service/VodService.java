package com.atshixin.vod.service;

import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String uploadVideo(MultipartFile file);
    void deleteVideoById(String id);
}
