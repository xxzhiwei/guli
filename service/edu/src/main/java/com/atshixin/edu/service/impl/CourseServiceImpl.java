package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Course;
import com.atshixin.edu.mapper.CourseMapper;
import com.atshixin.edu.service.CourseService;
import com.atshixin.edu.vo.CourseInfo;
import com.atshixin.edu.vo.CourseListItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-10
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Override
    public Page<CourseListItem> getCourses(Integer current, Integer size, QueryWrapper<Course> queryWrapper) {
        Page<CourseListItem> page = new Page<>(current, size);
        baseMapper.getCourses(page, queryWrapper);
        return page;
    }

    @Cacheable(value = "front", key = "'courses'")
    @Override
    public List<Course> getHotCourses() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 4");

        return list(queryWrapper);
    }

    @Override
    public CourseListItem getCourseById(String courseId) {
        return baseMapper.getCourseById(courseId);
    }
}
