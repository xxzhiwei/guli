package com.atshixin.edu.controller;

import com.atshixin.base.types.CallTypes;
import com.atshixin.edu.client.OrderFeignClient;
import com.atshixin.edu.common.DataTypes;
import com.atshixin.edu.common.OrderTypes;
import com.atshixin.edu.common.PagingDefaultParameters;
import com.atshixin.edu.entity.Comment;
import com.atshixin.edu.entity.Course;
import com.atshixin.edu.service.ChapterService;
import com.atshixin.edu.service.CommentService;
import com.atshixin.edu.service.CourseService;
import com.atshixin.edu.vo.*;
import com.atshixin.util.JWT;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private OrderFeignClient orderFeignClient;

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
    public R getCourseById(@PathVariable("id") String courseId,
                           @RequestParam(required = false, defaultValue = CallTypes.HTTP) String callType, HttpServletRequest httpServletRequest) {
        CourseListItem courseListItem = courseService.getCourseById(courseId);

        // 远程调用
        if (callType.equals(CallTypes.RPC)) {
            return R.ok().data("record", courseListItem);
        }
        List<ChapterTreeNode> nodes = chapterService.getChapterTreeNodesById(courseId);
        String userId = JWT.getUserIdByToken(httpServletRequest);
        boolean isPayOrder = false;
        if (!userId.equals("")) {
            isPayOrder = orderFeignClient.isPayOrder(userId, courseId);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("baseInfo", courseListItem);
        map.put("chapters", nodes);
        map.put("isPay", isPayOrder);
        return ResultHelper.format(map);
    }

    @PostMapping("/comments")
    public R addComment(@RequestBody AddCommentVo addCommentVo) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(addCommentVo, comment);
        commentService.save(comment);
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        return ResultHelper.format(commentVo);
    }

    @GetMapping("/{id}/comments")
    public R getCourseCommentsById(
            @RequestParam(value = "current", defaultValue = PagingDefaultParameters.CURRENT) Integer current,
            @RequestParam(value = "size", defaultValue = PagingDefaultParameters.SIZE) Integer size,
            @PathVariable("id") String courseId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        Page<CommentVo> comments;

        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("parent_id", "");
        comments = commentService.getCommentVos(current, size, queryWrapper);

        return ResultHelper.format(comments);
    }

    @GetMapping("/comments/parent/{parentId}")
    public R getSecondCourseCommentsById(
            @PathVariable(value = "parentId") String parentId,
            @RequestParam(value = "current", defaultValue = PagingDefaultParameters.CURRENT) Integer current,
            @RequestParam(value = "size", defaultValue = PagingDefaultParameters.SIZE) Integer size
    ) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
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

