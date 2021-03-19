package com.atshixin.edu.vo;

import com.atshixin.edu.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVo extends Comment {
    private ChildCommentVo data;
}
