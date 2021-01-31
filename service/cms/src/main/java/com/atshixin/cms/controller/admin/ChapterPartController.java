package com.atshixin.cms.controller.admin;


import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.cms.client.VodFeignClient;
import com.atshixin.cms.entity.ChapterPart;
import com.atshixin.cms.service.ChapterPartService;
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
@RestController("AdminChapterPartController")
@RequestMapping("/cms/admin/chapterPart")
@CrossOrigin
public class ChapterPartController {

    @Autowired
    private VodFeignClient vodFeignClient;

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
    public R updateChapterPartById(@PathVariable("id") String chapterId, @RequestBody ChapterPart chapterPart) {
        if (StringUtils.isEmpty(chapterPart.getId())) {
            chapterPart.setId(chapterId);
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
    public R deleteChapterPartById(@PathVariable(value = "id") String chapterId) {
        ChapterPart chapterPart = chapterPartService.getById(chapterId);
        String videoId = chapterPart.getVideoSourceId();

        if (!StringUtils.isEmpty(videoId)) {
            R result = vodFeignClient.deleteVideoById(videoId);
            if (!result.getSuccess()) {
                throw new GuliException(result.getCode(), result.getMessage());
            }
        }
        chapterPartService.removeById(chapterId);
        return R.ok();
    }
}

