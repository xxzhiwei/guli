package com.atshixin.edu.service;

import com.atshixin.edu.entity.ChapterPart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-11
 */
public interface ChapterPartService extends IService<ChapterPart> {

    void deleteChapterPartByCourseId(String id);
}
