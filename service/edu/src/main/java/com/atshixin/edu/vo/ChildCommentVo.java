package com.atshixin.edu.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// 这个和ResultHelper保持一致
@NoArgsConstructor
@Setter
@Getter
public class ChildCommentVo {
    private List<CommentVo> records = new ArrayList<>();
    private Long total = 0L;
    private Long pages = -1L;
    private Long current = -1L;
    private Long size = -1L;
    private Boolean hasNext = false;
    private Boolean hasPrevious = false;

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
