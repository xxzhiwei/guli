package com.atshixin.edu.controller;


import com.atshixin.edu.entity.Chapter;
import com.atshixin.edu.pojo.ChapterTreeNode;
import com.atshixin.edu.service.ChapterService;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-01-11
 */
@RestController
@RequestMapping("/edu/chapters")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @GetMapping("/treeNodes/{id}")
    public R getTreeNodesById(@PathVariable("id") String courseId) {
        List<ChapterTreeNode> chapterTreeNodes = chapterService.getChapterTreeNodesById(courseId);
        return ResultHelper.format(chapterTreeNodes);
    }

    @PostMapping
    public R addChapter(@RequestBody Chapter chapter) {

        boolean isOK = chapterService.save(chapter);

        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @PutMapping("/{id}")
    public R updateChapterById(@PathVariable("id") String chapterId, @RequestBody Chapter chapter) {
        if (StringUtils.isEmpty(chapter.getId())) {
            chapter.setId(chapterId);
        }
        boolean isOK = chapterService.updateById(chapter);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteChapterById(@PathVariable("id") String chapterId) {
        boolean isOK = chapterService.removeById(chapterId);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }
}

