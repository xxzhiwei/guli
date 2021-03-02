package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Teacher;
import com.atshixin.edu.mapper.TeacherMapper;
import com.atshixin.edu.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Page<Teacher> getTeachers(Integer current, Integer size, QueryWrapper<Teacher> queryWrapper) {
        Page<Teacher> page = new Page<>(current, size);

        this.page(page, queryWrapper);

        return page;
    }

    @Cacheable(value = "front", key = "'teachers'")
    @Override
    public List<Teacher> getTeachers() {

        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 4");

        return list(queryWrapper);
    }
}
