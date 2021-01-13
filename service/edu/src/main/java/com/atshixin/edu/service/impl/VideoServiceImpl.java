package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Video;
import com.atshixin.edu.mapper.VideoMapper;
import com.atshixin.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-11
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
