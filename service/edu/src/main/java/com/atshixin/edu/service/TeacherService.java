package com.atshixin.edu.service;

import com.atshixin.edu.entity.Teacher;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-02
 */
public interface TeacherService extends IService<Teacher> {
    Page<Teacher> getTeachers(Integer current, Integer size, QueryWrapper<Teacher> queryWrapper);

    List<Teacher> getTeachers();
}
