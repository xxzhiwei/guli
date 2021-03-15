package com.atshixin.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.util.ResultCode;
import com.atshixin.vod.service.VodService;
import com.atshixin.vod.util.PropertiesReader;
import com.atshixin.vod.util.VodClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                throw new GuliException(ResultCode.ERROR, "上传视频失败, 视频id: " + response.getVideoId() + "; 错误码: " + response.getCode() + "; 错误信息: " + response.getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR, "上传视频发生错误");
        }
    }

    @Override
    public void deleteVideoById(String videoId) {
        deleteVideoHandler(videoId);
    }


    @Override
    public void deleteVideosByIds(List<String> videoIds) {
        String idStr = StringUtils.join(videoIds, ",");
        deleteVideoHandler(idStr);
    }

    @Override
    public String getVideoPlayAuthById(String videoSourceId) {
        DefaultAcsClient client = VodClient.init(propertiesReader.getRegionId(), propertiesReader.getAssessKeyId(), propertiesReader.getAssessSecret());
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoSourceId);
        try {
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            return response.getPlayAuth();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(200001, "获取视频播放凭证失败：videoSourceId=" + videoSourceId);
        }
    }

    private void deleteVideoHandler(String idStr) {
        DefaultAcsClient client = VodClient.init(propertiesReader.getRegionId(), propertiesReader.getAssessKeyId(), propertiesReader.getAssessSecret());
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(idStr);
        try {
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR, "删除视频失败：" + idStr);
        }
    }

    public static void main(String[] args) {
        String fileOriginalFilename = "123456.mp4";
        String title = fileOriginalFilename.substring(0, fileOriginalFilename.indexOf("."));
        System.out.println(title);
    }
}
