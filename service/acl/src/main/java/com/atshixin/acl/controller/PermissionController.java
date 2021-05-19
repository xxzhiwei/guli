package com.atshixin.acl.controller;


import com.atshixin.acl.entity.Permission;
import com.atshixin.acl.entity.Role;
import com.atshixin.acl.service.PermissionService;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/acl/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public R getPermissions(@RequestParam(value = "current", defaultValue = "1") Integer current,
                            @RequestParam(value = "size", defaultValue = "10") Integer size,
                            @RequestParam(required = false) String name) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        Page<Permission> permissionPage = permissionService.getPermissions(current, size, queryWrapper);
        return ResultHelper.format(permissionPage);
    }
    public void getPermissionsByUserId() {}
    public void getPermissionsByRoleId() {}
    public void deletePermission() {}
    public void addPermission() {}
    public void updatePermission() {}

    public void assignPermissionsForRole() { }
}

