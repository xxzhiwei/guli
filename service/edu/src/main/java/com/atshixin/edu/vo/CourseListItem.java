package com.atshixin.edu.vo;

import com.atshixin.edu.entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseListItem extends Course {
    private String teacherName;
    private String subjectName;
    private String subjectParentName;
}
