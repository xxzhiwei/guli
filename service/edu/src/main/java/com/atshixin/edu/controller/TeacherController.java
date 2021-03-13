package com.atshixin.edu.controller;

import com.atshixin.edu.common.DataTypes;
import com.atshixin.edu.entity.Course;
import com.atshixin.edu.entity.Teacher;
import com.atshixin.edu.service.CourseService;
import com.atshixin.edu.service.TeacherService;
import com.atshixin.edu.vo.TeacherInfo;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-01-02
 */
@RestController
@RequestMapping("/edu/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @GetMapping
    public R getTeachers(
            @RequestParam(required = false, value = "type", defaultValue = DataTypes.PAGING) String type,
            @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size
    ) {
        if (type.equals(DataTypes.FAMOUS)) {
            List<Teacher> teachers = teacherService.getTeachers();
            return ResultHelper.format(teachers);
        }
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        Page<Teacher> teachers = teacherService.getTeachers(current, size, queryWrapper);
        return ResultHelper.format(teachers);
    }

    @GetMapping("/{id}")
    public R getTeacherById(@PathVariable("id") String teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        List<Course> courses = courseService.list(queryWrapper);
        TeacherInfo teacherInfo = new TeacherInfo();
        teacherInfo.setBaseInfo(teacher);
        teacherInfo.setCourses(courses);
        return ResultHelper.format(teacherInfo);
    }
}

