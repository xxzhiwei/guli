package com.atshixin.edu.vo;

import com.atshixin.edu.entity.Course;
import com.atshixin.edu.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class TeacherInfo {
    private Teacher baseInfo;
    private List<Course> courses;
}
