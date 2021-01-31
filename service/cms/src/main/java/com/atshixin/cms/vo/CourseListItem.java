package com.atshixin.cms.vo;

import com.atshixin.cms.entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseListItem extends Course {
    private String teacherName;
    private String subjectName;
    private String subjectParentName;
}
