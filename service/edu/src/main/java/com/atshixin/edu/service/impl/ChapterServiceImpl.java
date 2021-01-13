package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Chapter;
import com.atshixin.edu.entity.Course;
import com.atshixin.edu.entity.Video;
import com.atshixin.edu.mapper.ChapterMapper;
import com.atshixin.edu.pojo.ChapterTreeNode;
import com.atshixin.edu.service.ChapterService;
import com.atshixin.edu.service.VideoService;
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
    private VideoService videoService;

    @Override
    public List<ChapterTreeNode> getTreeNodesById(String courseId) {
        // 1. 查出所有课程对应章节
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);

        List<Chapter> chapters = list(chapterQueryWrapper);
        // 2. 找出章节对应小节(注意：这里的小节是指video
        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        List<Video> videos = videoService.list(videoQueryWrapper);

        List<ChapterTreeNode> chapterTreeNodes = new ArrayList<>();

        for (Chapter chapter : chapters) {
            ChapterTreeNode parentChapterTreeNode = new ChapterTreeNode();
            BeanUtils.copyProperties(chapter, parentChapterTreeNode);

            // 子节点
            List<ChapterTreeNode> subChapterTreeNodeList = new ArrayList<>();

            for (Video video : videos) {
                if (video.getChapterId().equals(chapter.getId())) {
                    ChapterTreeNode subChapterTreeNode = new ChapterTreeNode();
                    BeanUtils.copyProperties(video, subChapterTreeNode);
                    subChapterTreeNodeList.add(subChapterTreeNode);
                }
            }
            parentChapterTreeNode.setChildren(subChapterTreeNodeList);
            chapterTreeNodes.add(parentChapterTreeNode);
        }

        return chapterTreeNodes;
    }
}
