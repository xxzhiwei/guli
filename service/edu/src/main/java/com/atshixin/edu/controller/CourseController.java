package com.atshixin.edu.controller;


import com.atshixin.edu.entity.Course;
import com.atshixin.edu.service.CourseService;
import com.atshixin.edu.util.PagingHelper;
import com.atshixin.edu.vo.CourseInfo;
import com.atshixin.edu.vo.CourseListItem;
import com.atshixin.util.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public R getCourses(
            @RequestParam("pageIndex") Integer pageIndex,
            @RequestParam("pageSize") Integer pageSize,
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

        Page<CourseListItem> courses = courseService.getCourses(pageIndex, pageSize, courseQueryWrapper);

        return PagingHelper.paging(courses);
    }

    @GetMapping("/{id}")
    public R getCourseById(@PathVariable String id) {
        CourseInfo courseInfo = courseService.getCourseById(id);
        return R.ok().data("item", courseInfo);
    }

    @PostMapping
    public R addCourse(@RequestBody CourseInfo courseInfo) {

        String id = courseService.saveCourse(courseInfo);
        return R.ok().data("id", id);
    }

    @PutMapping("/{id}")
    public R updateCourse(@PathVariable String id, @RequestBody CourseInfo courseInfo) {
        if (StringUtils.isEmpty(courseInfo.getId())) {
            courseInfo.setId(id);
        }
        courseService.updateCourse(courseInfo);
        return R.ok();
    }
}

