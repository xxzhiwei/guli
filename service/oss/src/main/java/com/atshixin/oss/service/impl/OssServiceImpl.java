package com.atshixin.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.atshixin.oss.service.OssService;
import com.atshixin.oss.util.PropertiesReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private PropertiesReader propertiesReader;

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // Endpoint以杭州为例，其它Region请按实际情况填写
        String endpoint = propertiesReader.getEndPoint();
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = propertiesReader.getAssessKeyId();
        String accessKeySecret = propertiesReader.getAssessSecret();
        String bucketName = propertiesReader.getBucketName();
        String prefix = propertiesReader.getPrefix();
        String url = null;

        try {
            // 创建OSSClient实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String format = simpleDateFormat.format(date);

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = uuid + "-" + file.getOriginalFilename();

            String filePath = format + fileName;

            // 创建PutObjectRequest对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, filePath, new ByteArrayInputStream(file.getBytes()));

            // 上传字符串
            ossClient.putObject(putObjectRequest);

            // 关闭OSSClient
            ossClient.shutdown();
            // https://guli-shixin.oss-cn-guangzhou.aliyuncs.com/2021/01/06IMG_7115.PNG
            url = prefix + bucketName + "." + endpoint + "/" + filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(uuid);
    }
}
