package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.ChapterPart;
import com.atshixin.edu.mapper.ChapterPartMapper;
import com.atshixin.edu.service.ChapterPartService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class ChapterPartServiceImpl extends ServiceImpl<ChapterPartMapper, ChapterPart> implements ChapterPartService {

    @Override
    public void deleteChapterPartByCourseId(String id) {
        QueryWrapper<ChapterPart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", id);
        remove(queryWrapper);
    }
}
