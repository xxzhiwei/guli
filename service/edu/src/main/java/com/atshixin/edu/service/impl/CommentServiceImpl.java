package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Comment;
import com.atshixin.edu.mapper.CommentMapper;
import com.atshixin.edu.service.CommentService;
import com.atshixin.edu.vo.ChildCommentVo;
import com.atshixin.edu.vo.CommentVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public Page<CommentVo> getComments(Integer current, Integer size, QueryWrapper<Comment> queryWrapper) {
        Page<CommentVo> commentPage = new Page<>(current, size);
        baseMapper.getComments(commentPage, queryWrapper);
        return commentPage;
    }

    @Override
    public void updateCommentById(String commentId, String content) {
        baseMapper.updateCommentById(commentId, content);
    }

    @Override
    public Page<CommentVo> getCommentVos(Integer current, Integer size, Integer childSize, QueryWrapper<Comment> queryWrapper) {
        Page<CommentVo> commentVoPage = getComments(current, size, queryWrapper);

        for (CommentVo commentVo : commentVoPage.getRecords()) {
            // 获取二级评论
            QueryWrapper<Comment> childQueryWrapper = new QueryWrapper<>();
            childQueryWrapper.eq("topic_id", commentVo.getTopicId());
            childQueryWrapper.ne("reply_id", "");
            Page<CommentVo> childCommentPage = getComments(1, childSize, childQueryWrapper);

            ChildCommentVo childCommentVo = new ChildCommentVo(childCommentPage);
            commentVo.setData(childCommentVo);
        }
        return commentVoPage;
    }
}
