package com.atshixin.cms.pojo;

import com.atshixin.cms.entity.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SubjectTreeNode extends Subject {
    private List<SubjectTreeNode> children = new ArrayList<>();
}
