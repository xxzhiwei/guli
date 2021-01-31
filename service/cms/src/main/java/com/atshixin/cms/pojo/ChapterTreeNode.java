package com.atshixin.cms.pojo;

import com.atshixin.cms.entity.Chapter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ChapterTreeNode extends Chapter {
    private List<ChapterPartTreeNode> children = new ArrayList<>();
}
