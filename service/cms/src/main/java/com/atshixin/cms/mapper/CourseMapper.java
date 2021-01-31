package com.atshixin.cms.mapper;

import com.atshixin.cms.entity.Course;
import com.atshixin.cms.vo.CourseListItem;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author atshixin
 * @since 2021-01-10
 */
public interface CourseMapper extends BaseMapper<Course> {
    IPage<CourseListItem> getCourses(IPage<CourseListItem> page, @Param(Constants.WRAPPER) Wrapper<Course> queryWrapper);
}
