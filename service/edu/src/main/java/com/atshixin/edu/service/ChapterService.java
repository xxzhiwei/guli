package com.atshixin.edu.service;

import com.atshixin.edu.entity.Chapter;
import com.atshixin.edu.pojo.ChapterTreeNode;
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
