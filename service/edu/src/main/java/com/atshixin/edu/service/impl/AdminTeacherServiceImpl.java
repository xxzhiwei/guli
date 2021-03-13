package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Teacher;
import com.atshixin.edu.mapper.TeacherMapper;
import com.atshixin.edu.service.AdminTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdminTeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements AdminTeacherService {
    /**
     * 获取前端讲师
     * @param current 当前页码
     * @param size 每页数据条数
     * @param queryWrapper 查询参数
     * @return
     */
    @Override
    public Page<Teacher> getTeachers(Integer current, Integer size, QueryWrapper<Teacher> queryWrapper) {
        Page<Teacher> page = new Page<>(current, size);

        this.page(page, queryWrapper);

        return page;
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        return getById(teacherId);
    }
}
