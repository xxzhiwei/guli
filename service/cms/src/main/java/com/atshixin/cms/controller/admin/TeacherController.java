package com.atshixin.cms.controller.admin;

import com.atshixin.base.QueryParameter;
import com.atshixin.cms.entity.Teacher;
import com.atshixin.cms.service.TeacherService;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@RestController("AdminTeacherController")
@RequestMapping("/cms/admin/teachers")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 若传递「all」，则忽略其他参数查询全部
     */
    @GetMapping
    public R getTeachers(
            @RequestParam(value = "current", required = false) Integer current,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(required = false) String name, @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String begin, @RequestParam(required = false) String end,
            @RequestParam(required = false, defaultValue = "0") Integer all
    ) {

        if (all.equals(QueryParameter.ALL)) {
            List<Teacher> teachers = teacherService.list(null);
            return ResultHelper.format(teachers);
        }
        else {
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();

            if (!StringUtils.isEmpty(name)) {
                queryWrapper.like("name", name);
            }

            // 如果传入为null，该方法会返回true
            if (!StringUtils.isEmpty(level)) {
                queryWrapper.eq("level", level);
            }

            if (!StringUtils.isEmpty(begin)) {
                queryWrapper.ge("gmt_create", begin);
            }

            if (!StringUtils.isEmpty(end)) {
                queryWrapper.le("gmt_create", end);
            }

            Page<Teacher> page = teacherService.getTeachers(current, size, queryWrapper);

            return ResultHelper.format(page);
        }
    }

    @DeleteMapping("/{id}")
    public R deleteTeacher(@PathVariable("id") String teacherId) {
        boolean isOK = teacherService.removeById(teacherId);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @PostMapping
    public R addTeacher(@RequestBody Teacher teacher) {
        boolean isOK = teacherService.save(teacher);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @PutMapping("/{id}")
    public R updateTeacher(@PathVariable("id") String teacherId, @RequestBody Teacher teacher) {
        if (StringUtils.isEmpty(teacher.getId())) {
            teacher.setId(teacherId);
        }
        boolean isOK = teacherService.updateById(teacher);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @GetMapping("/{id}")
    public R getTeacher(@PathVariable("id") String teacherId) {
        Teacher teacher = teacherService.getById(teacherId);
        return ResultHelper.format(teacher);
    }
}

