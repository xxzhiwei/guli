package com.atshixin.edu.controller;


import com.atshixin.edu.service.CourseService;
import com.atshixin.edu.vo.CourseInfo;
import com.atshixin.util.R;
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

