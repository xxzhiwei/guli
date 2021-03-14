package com.atshixin.edu.controller;


import com.atshixin.edu.common.DataTypes;
import com.atshixin.edu.common.OrderTypes;
import com.atshixin.edu.entity.Course;
import com.atshixin.edu.service.ChapterService;
import com.atshixin.edu.service.CourseService;
import com.atshixin.edu.vo.ChapterTreeNode;
import com.atshixin.edu.vo.CourseInfo;
import com.atshixin.edu.vo.CourseListItem;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-01-10
 */
@RestController
@RequestMapping("/edu/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @GetMapping
    public R getCourses(
            @RequestParam(value = "current", defaultValue = "1") Integer current,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(required = false) String subjectParentId,
            @RequestParam(required = false) String subjectId,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = DataTypes.PAGING) String type
    ) {
        // 获取热门课程
        if (type.equals(DataTypes.HOT)) {
            List<Course> courses = courseService.getHotCourses();
            return ResultHelper.format(courses);
        }
        // 获取分页
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("ec.is_deleted", 0);

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("ec.subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("ec.subject_id", subjectId);
        }

        // 对应数据库中得字段
        if (!StringUtils.isEmpty(sort)) {
            if (sort.equals(OrderTypes.PRICE)) {
                queryWrapper.orderByDesc("ec.price");
            }
            if (sort.equals(OrderTypes.CREATE_TIME)) {
                queryWrapper.orderByDesc("ec.gmt_create");
            }
            if (sort.equals(OrderTypes.ATTENTION)) {
                queryWrapper.orderByDesc("ec.buy_count");
            }
        }

        Page<CourseListItem> courses = courseService.getCourses(current, size, queryWrapper);

        return ResultHelper.format(courses);
    }

    @GetMapping("{id}")
    public R getCourse(@PathVariable("id") String courseId) {
        CourseListItem courseListItem = courseService.getCourseById(courseId);
        List<ChapterTreeNode> nodes = chapterService.getChapterTreeNodesById(courseId);
        Map<String, Object> map = new HashMap<>();
        map.put("baseInfo", courseListItem);
        map.put("chapters", nodes);
        return ResultHelper.format(map);
    }
}

