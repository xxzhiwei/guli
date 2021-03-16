package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Comment;
import com.atshixin.edu.mapper.CommentMapper;
import com.atshixin.edu.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public Page<Comment> getCommentsById(Integer current, Integer size, QueryWrapper<Comment> queryWrapper) {
        Page<Comment> commentPage = new Page<>(current, size);
        this.page(commentPage, queryWrapper);
        return commentPage;
    }

    @Override
    public void updateCommentById(String commentId, String content) {
        baseMapper.updateCommentById(commentId, content);
    }
}
