package com.atshixin.acl.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class SamplePermissionTree extends PermissionVo {
    private List<SamplePermissionTree> children = new ArrayList<>();
}
