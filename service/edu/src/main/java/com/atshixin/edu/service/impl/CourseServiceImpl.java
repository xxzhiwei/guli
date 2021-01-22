package com.atshixin.edu.service.impl;

import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.edu.entity.Course;
import com.atshixin.edu.entity.CourseDescription;
import com.atshixin.edu.mapper.CourseMapper;
import com.atshixin.edu.service.ChapterService;
import com.atshixin.edu.service.CourseDescriptionService;
import com.atshixin.edu.service.CourseService;
import com.atshixin.edu.service.ChapterPartService;
import com.atshixin.edu.vo.CourseInfo;
import com.atshixin.edu.vo.CourseListItem;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private ChapterPartService chapterPartService;

    @Autowired
    private ChapterService chapterService;

    @Override
    public String saveCourse(CourseInfo courseInfo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo, course);
        save(course);

        String courseId = course.getId();
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseId);
        courseDescription.setDescription(courseInfo.getDescription());

        courseDescriptionService.save(courseDescription);
        return courseId;
    }

    @Override
    public void updateCourse(CourseInfo courseInfo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo, course);
        boolean isOK = updateById(course);

        if (!isOK) {
            throw new GuliException(20001, "更新课程失败");
        }

        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfo, courseDescription);

        boolean isCourseDescriptionOK = courseDescriptionService.updateById(courseDescription);
        if (!isCourseDescriptionOK) {
            throw new GuliException(20001, "更新课程失败");
        }
    }

    @Override
    public CourseInfo getCourseById(String id) {
        CourseInfo courseInfo = new CourseInfo();
        Course course = getById(id);

        // CourseDescription 的主键为course_id
        CourseDescription courseDescription = courseDescriptionService.getById(id);
        BeanUtils.copyProperties(course, courseInfo);
        courseInfo.setDescription(courseDescription.getDescription());
        return courseInfo;
    }

    @Override
    public Page<CourseListItem> getCourses(Integer pageIndex, Integer pageSize, QueryWrapper<Course> queryWrapper) {
        Page<CourseListItem> page = new Page<>(pageIndex, pageSize);
        baseMapper.getCourses(page, queryWrapper);
        return page;
    }

    @Override
    public void deleteCourseById(String id) {
        // 1. 根据课程id删小节
        chapterPartService.deleteChapterPartByCourseId(id);
        // 2. 根据课程id删章节
        chapterService.deleteChaptersByCourseId(id);
        // 3. 根据课程id删描述
        courseDescriptionService.removeById(id);
        // 4. 根据课程id删课程
        removeById(id);
    }
}
