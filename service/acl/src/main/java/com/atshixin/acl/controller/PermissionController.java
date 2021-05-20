package com.atshixin.acl.controller;


import com.atshixin.acl.entity.Permission;
import com.atshixin.acl.entity.Role;
import com.atshixin.acl.service.PermissionService;
import com.atshixin.acl.vo.PermissionVo;
import com.atshixin.acl.vo.SamplePermissionTree;
import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public R getPermissions() {
        List<SamplePermissionTree> permissionTrees = permissionService.getPermissionTrees();
        return ResultHelper.format(permissionTrees);
    }
    public void getPermissionsByUserId() {}
    public void getPermissionsByRoleId() {}

    @DeleteMapping("/{permissionId}")
    public R deletePermission(@PathVariable String permissionId) {
        permissionService.removeById(permissionId);
        return R.ok();
    }
    @PostMapping
    public R addPermission(@RequestBody PermissionVo addPermissionVo) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(addPermissionVo, permission);
        permissionService.save(permission);
        return R.ok();
    }

    @PutMapping
    public R updatePermission(@RequestBody PermissionVo updatePermissionVo) {
        if (StringUtils.isEmpty(updatePermissionVo.getId())) {
            throw new GuliException(20001234, "权限id不能为空");
        }

        Permission permission = new Permission();
        BeanUtils.copyProperties(updatePermissionVo, permission);
        permissionService.updateById(permission);
        return R.ok();
    }

    public void assignPermissionsForRole() { }
}

