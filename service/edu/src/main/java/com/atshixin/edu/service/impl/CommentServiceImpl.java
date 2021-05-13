package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.Comment;
import com.atshixin.edu.mapper.CommentMapper;
import com.atshixin.edu.service.CommentService;
import com.atshixin.edu.vo.ChildCommentCountVo;
import com.atshixin.edu.vo.ChildCommentVo;
import com.atshixin.edu.vo.CommentVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public Page<CommentVo> getCommentVos(Integer current, Integer size, QueryWrapper<Comment> queryWrapper) {
        Page<CommentVo> commentVoPage = getComments(current, size, queryWrapper);
        List<String> parentIds = commentVoPage.getRecords().stream().map(Comment::getId).collect(Collectors.toList());

        // 获取指定parentId的前n条子评论
        List<CommentVo> topChildCommentVos = baseMapper.getTopChildCommentsByParentIds(parentIds, 3);

        // 获取指定parentId的总子评论数
        List<ChildCommentCountVo> childCommentCountList = baseMapper.getChildCommentCountByParentIds(parentIds);

        for (CommentVo commentVo : commentVoPage.getRecords()) {
            ChildCommentVo childCommentVo = new ChildCommentVo();
            List<CommentVo> topChildComments = topChildCommentVos.stream().filter(topChildCommentVo -> topChildCommentVo.getParentId().equals(commentVo.getId())).collect(Collectors.toList());
            childCommentVo.setRecords(topChildComments);
            // 删除topChildCommentVos中的评论
            topChildCommentVos.removeAll(topChildComments);
            for (ChildCommentCountVo childCommentCountVo : childCommentCountList) {
                if (childCommentCountVo.getParentId().equals(commentVo.getId())) {
                    childCommentVo.setTotal((long) childCommentCountVo.getCount());
                    // 删除子评论数
                    childCommentCountList.remove(childCommentCountVo);
                    break;
                }
            }
            commentVo.setData(childCommentVo);
        }
        return commentVoPage;
    }
}
