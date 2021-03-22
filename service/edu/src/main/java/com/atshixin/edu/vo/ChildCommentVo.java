package com.atshixin.edu.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// 这个和ResultHelper保持一致
@Setter
@Getter
public class ChildCommentVo {
    private List<CommentVo> records;
    private Long total;
    private Long pages;
    private Long current;
    private Long size;
    private Boolean hasNext;
    private Boolean hasPrevious;

    public ChildCommentVo(Page<CommentVo> commentVoPage) {
        records = commentVoPage.getRecords();
        total = commentVoPage.getTotal();
        pages = commentVoPage.getPages();
        current = commentVoPage.getCurrent();
        size = commentVoPage.getSize();
        hasNext = commentVoPage.hasNext();
        hasPrevious = commentVoPage.hasPrevious();
    }
}
