package com.atshixin.edu.controller;


import com.atshixin.edu.entity.ChapterPart;
import com.atshixin.edu.service.ChapterPartService;
import com.atshixin.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器(章节小节)
 * </p>
 *
 * @author atshixin
 * @since 2021-01-11
 */
@RestController
@RequestMapping("/edu/chapterPart")
@CrossOrigin
public class ChapterPartController {

    @Autowired
    private ChapterPartService chapterPartService;

    @PostMapping
    public R addChapterPart(@RequestBody ChapterPart chapterPart) {
        boolean isOK = chapterPartService.save(chapterPart);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @PutMapping("/{id}")
    public R updateChapterPartById(@PathVariable String id, @RequestBody ChapterPart chapterPart) {
        if (StringUtils.isEmpty(chapterPart.getId())) {
            chapterPart.setId(id);
        }
        boolean isOK = chapterPartService.updateById(chapterPart);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @DeleteMapping("/{id}")
    public R deleteChapterPartById(@PathVariable(value = "id") String id) {
        boolean isOK = chapterPartService.removeById(id);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }
}

