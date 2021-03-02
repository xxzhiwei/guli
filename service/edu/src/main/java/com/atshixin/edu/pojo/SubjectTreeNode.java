package com.atshixin.edu.pojo;

import com.atshixin.edu.entity.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SubjectTreeNode extends Subject {
    private List<SubjectTreeNode> children = new ArrayList<>();
}
