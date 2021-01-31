package com.atshixin.cms.service;

import com.atshixin.cms.pojo.ChapterTreeNode;
import com.atshixin.cms.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-11
 */
public interface ChapterService extends IService<Chapter> {
    List<ChapterTreeNode> getChapterTreeNodesById(String courseId);
    void deleteChaptersById(String courseId);
}
