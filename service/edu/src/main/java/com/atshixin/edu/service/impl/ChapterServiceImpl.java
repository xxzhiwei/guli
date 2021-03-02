package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Chapter;
import com.atshixin.edu.entity.ChapterPart;
import com.atshixin.edu.pojo.ChapterTreeNode;
import com.atshixin.edu.service.ChapterPartService;
import com.atshixin.edu.service.ChapterService;
import com.atshixin.edu.mapper.ChapterMapper;
import com.atshixin.edu.pojo.ChapterPartTreeNode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-11
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private ChapterPartService chapterPartService;

    @Override
    public List<ChapterTreeNode> getChapterTreeNodesById(String courseId) {
        // 1. 查出所有课程对应章节
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);

        List<Chapter> chapters = list(chapterQueryWrapper);

        // 2. 找出章节对应小节
        QueryWrapper<ChapterPart> chapterPartQueryWrapper = new QueryWrapper<>();
        chapterPartQueryWrapper.eq("course_id", courseId);

        // 小节列表
        List<ChapterPart> chapterPartList = chapterPartService.list(chapterPartQueryWrapper);

        List<ChapterTreeNode> chapterTreeNodes = new ArrayList<>();

        for (Chapter chapter : chapters) {
            // 父节点会有一些多于的属性
            ChapterTreeNode parentChapterTreeNode = new ChapterTreeNode();
            BeanUtils.copyProperties(chapter, parentChapterTreeNode);

            // 子节点
            List<ChapterPartTreeNode> chapterPartTreeNodes = new ArrayList<>();

            for (ChapterPart chapterPart : chapterPartList) {
                if (chapterPart.getChapterId().equals(chapter.getId())) {
                    ChapterPartTreeNode subChapterTreeNode = new ChapterPartTreeNode();
                    BeanUtils.copyProperties(chapterPart, subChapterTreeNode);
                    chapterPartTreeNodes.add(subChapterTreeNode);
                }
            }
            parentChapterTreeNode.setChildren(chapterPartTreeNodes);
            chapterTreeNodes.add(parentChapterTreeNode);
        }

        return chapterTreeNodes;
    }

    @Override
    public void deleteChaptersById(String courseId) {
        QueryWrapper<Chapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);

        remove(queryWrapper);
    }
}
