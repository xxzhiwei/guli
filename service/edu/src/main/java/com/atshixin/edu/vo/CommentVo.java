package com.atshixin.edu.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentVo {
    private String content;
    private String courseId;
    private String teacherId;
    private String memberId;
    private String nickname;
    private String avatar;
    private String parentId;
}
