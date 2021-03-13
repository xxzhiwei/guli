package com.atshixin.edu.service;

import com.atshixin.edu.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminTeacherService extends IService<Teacher> {
    Page<Teacher> getTeachers(Integer current, Integer size, QueryWrapper<Teacher> queryWrapper);
    Teacher getTeacherById(String teacherId);
}
