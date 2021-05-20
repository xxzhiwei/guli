package com.atshixin.acl.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PermissionVo {
    private String id;
    private String parentId;
    private Integer type;
    private String name;
    private String code;
    private Integer status;
    private String icon;
}
