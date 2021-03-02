package com.atshixin.edu.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CourseInfo {
    private String id;
    private String teacherId;
    private String subjectId;
    private String subjectParentId;
    private String title;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private String description;
    private String status;
}
