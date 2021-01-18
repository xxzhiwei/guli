package com.atshixin.edu.mapper;

import com.atshixin.edu.entity.Course;
import com.atshixin.edu.vo.CourseListItem;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
