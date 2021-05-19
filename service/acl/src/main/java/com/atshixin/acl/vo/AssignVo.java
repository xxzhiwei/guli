package com.atshixin.acl.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AssignVo {
    private String userId;
    private List<String> roleIds;
}
