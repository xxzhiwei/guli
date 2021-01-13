package com.atshixin.edu.controller;

import com.atshixin.edu.entity.Teacher;
import com.atshixin.edu.service.TeacherService;
import com.atshixin.util.R;
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
@RestController
@RequestMapping("/edu/teachers")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 默认是分页，其他需要在定义路由；如想要获取全部的话，应该为/edu/teachers/all
     */
    @GetMapping
    public R getTeachers(
            @RequestParam Integer pageIndex, @RequestParam Integer pageSize,
            @RequestParam(required = false) String name, @RequestParam(required = false) Integer level,
            @RequestParam(required = false) String begin, @RequestParam(required = false) String end
    ) {
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

        Page<Teacher> page = new Page<>(pageIndex, pageSize);

        // 查询后，page()会将结果封装到page中
        teacherService.page(page, queryWrapper);

        return R.ok()
                .data("total", page.getTotal())
                .data("totalPage", page.getPages())
                .data("items", page.getRecords());
    }

    @GetMapping("/all")
    public R getTeachers() {
        List<Teacher> teachers = teacherService.list(null);
        return R.ok().data("items", teachers);
    }

    @DeleteMapping("/{id}")
    public R deleteTeacher(@PathVariable String id) {
        boolean isOK = teacherService.removeById(id);
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
    public R updateTeacher(@PathVariable String id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        boolean isOK = teacherService.updateById(teacher);
        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @GetMapping("/{id}")
    public R getTeacher(@PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }
}

