package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Teacher;
import com.atshixin.edu.mapper.TeacherMapper;
import com.atshixin.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-02
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public Page<Teacher> getTeachers(Integer pageIndex, Integer pageSize, QueryWrapper<Teacher> queryWrapper) {
        Page<Teacher> page = new Page<>(pageIndex, pageSize);

        this.page(page, queryWrapper);

        return page;
    }
}
