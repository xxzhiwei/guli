package com.atshixin.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.vod.service.VodService;
import com.atshixin.vod.util.PropertiesReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VodServiceImpl implements VodService {

    @Autowired
    PropertiesReader propertiesReader;

    @Override
    public String uploadVideo(MultipartFile file) {

        try {
            String fileName = file.getOriginalFilename();
            String title = fileName.substring(0, fileName.indexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(propertiesReader.getAssessKeyId(), propertiesReader.getAssessSecret(), title, fileName, file.getInputStream());
            UploadVideoImpl uploader = new UploadVideoImpl();

            UploadStreamResponse response = uploader.uploadStream(request);

            if (response.isSuccess()) {
                return response.getVideoId();
            }
            else {
                throw new GuliException(20001, "上传视频失败, 视频id: " + response.getVideoId() + "; 错误码: " + response.getCode() + "; 错误信息: " + response.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(20001, "上传视频发生错误");
        }
    }

    @Override
    public void deleteVideoById(String id) {
        // todo
    }

    public static void main(String[] args) {
        String fileOriginalFilename = "123456.mp4";
        String title = fileOriginalFilename.substring(0, fileOriginalFilename.indexOf("."));
        System.out.println(title);
    }
}
