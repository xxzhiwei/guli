package com.atshixin.edu.service;

import com.atshixin.edu.entity.Course;
import com.atshixin.edu.vo.CourseInfo;
import com.atshixin.edu.vo.CourseListItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-10
 */
public interface CourseService extends IService<Course> {

    String saveCourse(CourseInfo courseInfo);
    void updateCourse(CourseInfo courseInfo);
    CourseInfo getCourseById(String id);
    Page<CourseListItem> getCourses(Integer pageIndex, Integer pageSize, QueryWrapper<Course> queryWrapper);
}
