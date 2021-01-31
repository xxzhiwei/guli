package com.atshixin.cms.service;

import com.atshixin.cms.entity.Course;
import com.atshixin.cms.vo.CourseInfo;
import com.atshixin.cms.vo.CourseListItem;
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
    CourseInfo getCourseById(String courseId);
    Page<CourseListItem> getCourses(Integer current, Integer size, QueryWrapper<Course> queryWrapper);
    void deleteCourseById(String courseId);

    void updateCourseStatusById(String courseId, String status);

    List<Course> getCourses();
}
