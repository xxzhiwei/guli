package com.atshixin.edu.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChildCommentVo {
    private List<CommentVo> records;
    private Long total;
    private Long pages;
    private Boolean hasNext;
    private Boolean hasPrevious;
    private boolean showPaging = false;

    public ChildCommentVo(Page<CommentVo> commentVoPage) {
        records = commentVoPage.getRecords();
        total = commentVoPage.getTotal();
        pages = commentVoPage.getPages();
        hasNext = commentVoPage.hasNext();
        hasPrevious = commentVoPage.hasPrevious();
    }
}
