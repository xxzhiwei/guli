package com.atshixin.edu.mapper;

import com.atshixin.edu.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface CommentMapper extends BaseMapper<Comment> {
    void updateCommentById(String commentId, String content);
}
