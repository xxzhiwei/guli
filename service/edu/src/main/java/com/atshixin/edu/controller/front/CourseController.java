package com.atshixin.edu.controller.front;


import com.atshixin.edu.entity.Course;
import com.atshixin.edu.service.CourseService;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-01-10
 */
@RestController
@RequestMapping("/cms/courses")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public R getCourses() {
        List<Course> courses = courseService.getCourses();
        return ResultHelper.format(courses);
    }

    @GetMapping("{id}")
    public R getCourse(@PathVariable("id") String courseId) {
        Course course = courseService.getById(courseId);
        return ResultHelper.format(course);
    }
}

