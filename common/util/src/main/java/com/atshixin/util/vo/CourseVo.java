package com.atshixin.util.vo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CourseVo {
    private String id;

    private String teacherId;

    private String subjectId;

    private String subjectParentId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private Long buyCount;

    private Long viewCount;

    private Long version;

    private String status;

    private Integer isDeleted;
    private String teacherName;
    private String subjectName;
    private String subjectParentName;

    private Date gmtCreate;

    private Date gmtModified;
}
