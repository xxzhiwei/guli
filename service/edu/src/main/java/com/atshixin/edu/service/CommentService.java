package com.atshixin.edu.service;

import com.atshixin.edu.entity.Comment;
import com.atshixin.edu.vo.CommentVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface CommentService extends IService<Comment> {
    Page<CommentVo> getComments(Integer current, Integer size, QueryWrapper<Comment> queryWrapper);
    void updateCommentById(String commentId, String content);
    Page<CommentVo> getCommentVos(Integer current, Integer size, QueryWrapper<Comment> queryWrapper);
}
