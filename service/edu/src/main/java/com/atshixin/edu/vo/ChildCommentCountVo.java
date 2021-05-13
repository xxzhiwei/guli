package com.atshixin.edu.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChildCommentCountVo {
    private String parentId;
    private Integer count;
}
