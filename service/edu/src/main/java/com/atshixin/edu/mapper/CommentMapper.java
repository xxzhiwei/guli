package com.atshixin.edu.mapper;

import com.atshixin.edu.entity.Comment;
import com.atshixin.edu.vo.ChildCommentCountVo;
import com.atshixin.edu.vo.CommentVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    void updateCommentById(String commentId, String content);
    IPage<CommentVo> getComments(IPage<CommentVo> page, @Param(Constants.WRAPPER) Wrapper<Comment> queryWrapper);
    List<CommentVo> getTopChildCommentsByParentIds(List<String> parentIds, Integer top);
    List<ChildCommentCountVo> getChildCommentCountByParentIds(List<String> parentIds);
}
