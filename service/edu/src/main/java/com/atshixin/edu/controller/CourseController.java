package com.atshixin.edu.controller;

import com.atshixin.edu.common.DataTypes;
import com.atshixin.edu.common.OrderTypes;
import com.atshixin.edu.common.PagingDefaultParameters;
import com.atshixin.edu.entity.Comment;
import com.atshixin.edu.entity.Course;
import com.atshixin.edu.service.ChapterService;
import com.atshixin.edu.service.CommentService;
import com.atshixin.edu.service.CourseService;
import com.atshixin.edu.vo.*;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-01-10
 */
@RestController
@RequestMapping("/edu/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public R getCourses(
            @RequestParam(value = "current", defaultValue = "1") Integer current,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(required = false) String subjectParentId,
            @RequestParam(required = false) String subjectId,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false, defaultValue = DataTypes.PAGING) String type
    ) {
        // 获取热门课程
        if (type.equals(DataTypes.HOT)) {
            List<Course> courses = courseService.getHotCourses();
            return ResultHelper.format(courses);
        }
        // 获取分页
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("ec.is_deleted", 0);

        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("ec.subject_parent_id", subjectParentId);
        }

        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("ec.subject_id", subjectId);
        }

        // 对应数据库中得字段
        if (!StringUtils.isEmpty(sort)) {
            if (sort.equals(OrderTypes.PRICE)) {
                queryWrapper.orderByDesc("ec.price");
            }
            if (sort.equals(OrderTypes.CREATE_TIME)) {
                queryWrapper.orderByDesc("ec.gmt_create");
            }
            if (sort.equals(OrderTypes.ATTENTION)) {
                queryWrapper.orderByDesc("ec.buy_count");
            }
        }

        Page<CourseListItem> courses = courseService.getCourses(current, size, queryWrapper);

        return ResultHelper.format(courses);
    }

    @GetMapping("/{id}")
    public R getCourse(@PathVariable("id") String courseId) {
        CourseListItem courseListItem = courseService.getCourseById(courseId);
        List<ChapterTreeNode> nodes = chapterService.getChapterTreeNodesById(courseId);
        Map<String, Object> map = new HashMap<>();
        map.put("baseInfo", courseListItem);
        map.put("chapters", nodes);
        return ResultHelper.format(map);
    }

    @PostMapping("/comments")
    public R addComment(@RequestBody AddCommentVo addCommentVo) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(addCommentVo, comment);

        // 二级评论必定有topicId，否则为一级评论
        if (StringUtils.isEmpty(comment.getTopicId())) {
            DefaultIdentifierGenerator identifierGenerator = new DefaultIdentifierGenerator();
            comment.setTopicId(identifierGenerator.nextId(new Object()).toString());
        }

        commentService.save(comment);
        return R.ok();
    }

    @GetMapping("/{id}/comments")
    public R getCourseCommentsById(
            @RequestParam(value = "current", defaultValue = PagingDefaultParameters.CURRENT) Integer current,
            @RequestParam(value = "size", defaultValue = PagingDefaultParameters.SIZE) Integer size,
            @RequestParam(value = "childSize", defaultValue = PagingDefaultParameters.SIZE) Integer childSize,
            @PathVariable("id") String courseId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        Page<CommentVo> comments;

        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("reply_id", "");
        comments = commentService.getCommentVos(current, size, queryWrapper, childSize);

        return ResultHelper.format(comments);
    }

    @GetMapping("/comments/topic/{topicId}/reply/{replyId}")
    public R getSecondCourseCommentsById(
            @PathVariable(value = "replyId") String replyId,
            @PathVariable(value = "topicId") String topicId,
            @RequestParam(value = "current", defaultValue = PagingDefaultParameters.CURRENT) Integer current,
            @RequestParam(value = "size", defaultValue = PagingDefaultParameters.SIZE) Integer size
    ) {
        // courseId需要用到吗？
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topic_id", topicId);
        queryWrapper.eq("reply_id", replyId);
        Page<CommentVo> comments = commentService.getComments(current, size, queryWrapper);
        return ResultHelper.format(comments);
    }

    // 只做两级评论（即直接评论课程为一级评论，
    @DeleteMapping("/comments/{id}")
    public R addComment(@PathVariable("id") String commentId) {
        commentService.removeById(commentId);
        return R.ok();
    }

    @PatchMapping("/comments/{id}")
    public R updateCommentById(@PathVariable("id") String commentId, @RequestBody UpdateCommentVo updateCommentVo) {
        commentService.updateCommentById(commentId, updateCommentVo.getContent());
        return R.ok();
    }

    public static void main(String[] args) {
        DefaultIdentifierGenerator identifierGenerator = new DefaultIdentifierGenerator();
        System.out.println(identifierGenerator.nextId(new Object()));
    }
}

