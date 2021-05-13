package com.atshixin.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_comment")
@ApiModel(value="Comment对象", description="评论")
public class Comment {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "课程id")
    private String courseId;
    @ApiModelProperty(value = "讲师id")
    private String teacherId;
    @ApiModelProperty(value = "会员id")
    private String memberId;
    @ApiModelProperty(value = "会员昵称")
    private String nickname;
    @ApiModelProperty(value = "会员头像")
    private String avatar;

    @ApiModelProperty(value = "父评论id")
    private String parentId;

    @ApiModelProperty(value = "回复评论的id")
    private String replyId;

    @ApiModelProperty(value = "回复对象")
    private String replyTo;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
}
