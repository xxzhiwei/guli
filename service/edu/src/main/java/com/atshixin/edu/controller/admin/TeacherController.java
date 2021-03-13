package com.atshixin.edu.controller.admin;

import com.atshixin.edu.common.DataTypes;
import com.atshixin.edu.entity.Teacher;
import com.atshixin.edu.service.AdminTeacherService;
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
@RequestMapping("/edu/admin/teachers")
public class TeacherController {

    @Autowired
    private AdminTeacherService adminTeacherService;

    @GetMapping
    public R getTeachers(
            @RequestParam(value = "current", required = false, defaultValue = "1") Integer current,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false) String name, @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String begin, @RequestParam(required = false) String end,
            @RequestParam(required = false, defaultValue = DataTypes.PAGING) String type
    ) {
        if (type.equals(DataTypes.ALL)) {
            List<Teacher> teachers = adminTeacherService.list(null);
            return ResultHelper.format(teachers);
        }
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

        Page<Teacher> page = adminTeacherService.getTeachers(current, size, queryWrapper);

        return ResultHelper.format(page);
    }

    @DeleteMapping("/{id}")
    public R deleteTeacher(@PathVariable("id") String teacherId) {
        boolean isOK = adminTeacherService.removeById(teacherId);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @PostMapping
    public R addTeacher(@RequestBody Teacher teacher) {
        boolean isOK = adminTeacherService.save(teacher);
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
        boolean isOK = adminTeacherService.updateById(teacher);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @GetMapping("/{id}")
    public R getTeacher(@PathVariable("id") String teacherId) {
        Teacher teacher = adminTeacherService.getById(teacherId);
        return ResultHelper.format(teacher);
    }
}

