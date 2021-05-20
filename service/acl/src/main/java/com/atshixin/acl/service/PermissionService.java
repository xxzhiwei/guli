package com.atshixin.acl.service;

import com.atshixin.acl.entity.Permission;
import com.atshixin.acl.vo.SamplePermissionTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
public interface PermissionService extends IService<Permission> {

    List<SamplePermissionTree> getPermissionTrees();
    List<Permission> getPermissionsByUserId(String userId);
    List<Permission> getPermissionsByRoleId(String roleId);
    void assignPermissionsForRole(String roleId, List<String> permissionIds);
}
