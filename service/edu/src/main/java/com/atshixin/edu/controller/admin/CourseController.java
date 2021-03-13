package com.atshixin.edu.controller.admin;


import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.edu.service.AdminCourseService;
import com.atshixin.edu.entity.Course;
import com.atshixin.edu.vo.CourseInfo;
import com.atshixin.edu.vo.CourseListItem;
import com.atshixin.util.R;
import com.atshixin.util.ResultCode;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-01-10
 */
@RestController("AdminCourseController")
@RequestMapping("/edu/admin/courses")
@CrossOrigin
public class CourseController {

    @Autowired
    private AdminCourseService adminCourseService;

    @GetMapping
    public R getCourses(
            @RequestParam(value = "current", required = false) Integer current,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "begin", required = false) String begin,
            @RequestParam(value = "end", required = false) String end,
            @RequestParam(value = "teacherId", required = false) String teacherId,
            @RequestParam(value = "subjectId", required = false) String subjectId,
            @RequestParam(value = "subjectParentId", required = false) String subjectParentId,
            @RequestParam(value = "minPrice", required = false) String minPrice,
            @RequestParam(value = "maxPrice", required = false) String maxPrice
    ) {

        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();

        courseQueryWrapper.ne("ec.is_deleted", 1);

        if (!StringUtils.isEmpty(title)) {
            courseQueryWrapper.like("ec.title", title);
        }

        if (!StringUtils.isEmpty(begin)) {
            courseQueryWrapper.ge("ec.gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            courseQueryWrapper.le("ec.gmt_create", end);
        }

        if (!StringUtils.isEmpty(teacherId)) {
            courseQueryWrapper.eq("ec.teacher_id", teacherId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            courseQueryWrapper.eq("ec.subject_id", subjectId);
        }

        if (!StringUtils.isEmpty(subjectParentId)) {
            courseQueryWrapper.eq("ec.subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(minPrice)) {
            courseQueryWrapper.ge("ec.price", minPrice);
        }

        if (!StringUtils.isEmpty(minPrice)) {
            courseQueryWrapper.le("ec.price", maxPrice);
        }

        Page<CourseListItem> page = adminCourseService.getCourses(current, size, courseQueryWrapper);

        return ResultHelper.format(page);
    }

    @GetMapping("/{id}")
    public R getCourseById(@PathVariable String id) {
        CourseInfo courseInfo = adminCourseService.getCourseById(id);
        return ResultHelper.format(courseInfo);
    }

    @PostMapping
    public R addCourse(@RequestBody CourseInfo courseInfo) {

        String id = adminCourseService.saveCourse(courseInfo);
        return R.ok().data("id", id);
    }

    @PutMapping("/{id}")
    public R updateCourse(@PathVariable("id") String courseId, @RequestBody CourseInfo courseInfo) {
        if (StringUtils.isEmpty(courseInfo.getId())) {
            courseInfo.setId(courseId);
        }
        adminCourseService.updateCourse(courseInfo);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R deleteCourseById(@PathVariable("id") String courseId) {
        adminCourseService.deleteCourseById(courseId);
        return R.ok();
    }

    @PatchMapping("/{id}/status")
    public R updateCourseStatusById(@PathVariable("id") String courseId, @RequestBody Map<String, String> parametersMap) {
        String status = parametersMap.get("status");

        if (StringUtils.isEmpty(status)) {
            throw new GuliException(ResultCode.ERROR, "状态不能为空");
        }
        adminCourseService.updateCourseStatusById(courseId, status);
        return R.ok();
    }
}

