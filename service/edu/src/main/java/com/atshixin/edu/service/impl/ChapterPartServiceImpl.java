package com.atshixin.edu.service.impl;

import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.edu.entity.ChapterPart;
import com.atshixin.edu.service.ChapterPartService;
import com.atshixin.edu.client.VodFeignClient;
import com.atshixin.edu.mapper.ChapterPartMapper;
import com.atshixin.util.R;
import com.atshixin.util.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-11
 */
@Service
public class ChapterPartServiceImpl extends ServiceImpl<ChapterPartMapper, ChapterPart> implements ChapterPartService {

    @Autowired
    private VodFeignClient vodFeignClient;

    // 通过课程id删除小节&视频
    @Override
    public void deleteChapterPartById(String courseId) {
        QueryWrapper<ChapterPart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<ChapterPart> chapterParts = list(queryWrapper);

        List<String> videoIds = new ArrayList<>();
        List<String> chapterPartIds = new ArrayList<>();
        for (ChapterPart chapterPart : chapterParts) {
            String videoSourceId = chapterPart.getVideoSourceId();

            if (!StringUtils.isEmpty(videoSourceId)) {
                videoIds.add(videoSourceId);
            }
            chapterPartIds.add(chapterPart.getId());
        }

        if (videoIds.size() > 0) {
            R result = vodFeignClient.deleteVideosByIds(videoIds);
            if (!result.getSuccess()) {
                throw new GuliException(ResultCode.ERROR, "删除章节失败");
            }
        }

        removeByIds(chapterPartIds);
    }
}
