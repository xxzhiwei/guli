package com.atshixin.cms.controller.front;

import com.atshixin.cms.entity.Teacher;
import com.atshixin.cms.service.TeacherService;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
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
@RequestMapping("/cms/teachers")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public R getTeachers() {

        List<Teacher> teachers = teacherService.getTeachers();
        return ResultHelper.format(teachers);
    }
}

