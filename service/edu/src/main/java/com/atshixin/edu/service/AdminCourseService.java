package com.atshixin.edu.service;

import com.atshixin.edu.entity.Course;
import com.atshixin.edu.entity.CrmBanner;
import com.atshixin.edu.vo.CourseInfo;
import com.atshixin.edu.vo.CourseListItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminCourseService extends IService<Course> {
    String saveCourse(CourseInfo courseInfo);
    void updateCourse(CourseInfo courseInfo);
    CourseInfo getCourseById(String courseId);
    Page<CourseListItem> getCourses(Integer current, Integer size, QueryWrapper<Course> queryWrapper);
    void deleteCourseById(String courseId);
    void updateCourseStatusById(String courseId, String status);
}
