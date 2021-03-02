package com.atshixin.edu.service.impl;

import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.edu.entity.Course;
import com.atshixin.edu.entity.CourseDescription;
import com.atshixin.edu.mapper.CourseMapper;
import com.atshixin.edu.service.ChapterPartService;
import com.atshixin.edu.service.ChapterService;
import com.atshixin.edu.service.CourseDescriptionService;
import com.atshixin.edu.service.CourseService;
import com.atshixin.edu.vo.CourseInfo;
import com.atshixin.edu.vo.CourseListItem;
import com.atshixin.util.ResultCode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new GuliException(ResultCode.ERROR, "更新课程失败");
        }

        CourseDescription courseDescription = new CourseDescription();
        BeanUtils.copyProperties(courseInfo, courseDescription);

        boolean isCourseDescriptionOK = courseDescriptionService.updateById(courseDescription);
        if (!isCourseDescriptionOK) {
            throw new GuliException(ResultCode.ERROR, "更新课程失败");
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
    public Page<CourseListItem> getCourses(Integer current, Integer size, QueryWrapper<Course> queryWrapper) {
        Page<CourseListItem> page = new Page<>(current, size);
        baseMapper.getCourses(page, queryWrapper);
        return page;
    }

    @Override
    public void deleteCourseById(String courseId) {
        // 1. 根据课程id删小节
        chapterPartService.deleteChapterPartById(courseId);
        // 2. 根据课程id删章节
        chapterService.deleteChaptersById(courseId);
        // 3. 根据课程id删描述
        courseDescriptionService.removeById(courseId);
        // 4. 根据课程id删课程
        removeById(courseId);
    }

    @Override
    public void updateCourseStatusById(String courseId, String status) {
        Course course = getById(courseId);
        if (course.getStatus().equals(status)) {
            throw new GuliException(ResultCode.ERROR, "无需更新状态，courseId：" + courseId);
        }
        course.setStatus(status);
        this.updateById(course);
    }

    @Cacheable(value = "front", key = "'courses'")
    @Override
    public List<Course> getCourses() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 8");

        return list(queryWrapper);
    }
}
