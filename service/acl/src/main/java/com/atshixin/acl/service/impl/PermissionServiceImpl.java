package com.atshixin.acl.service.impl;

import com.atshixin.acl.entity.Permission;
import com.atshixin.acl.entity.RolePermission;
import com.atshixin.acl.mapper.PermissionMapper;
import com.atshixin.acl.service.PermissionService;
import com.atshixin.acl.service.RolePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionService permissionService;

    @Override
    public Page<Permission> getPermissions(Integer current, Integer size, QueryWrapper<Permission> queryWrapper) {
        Page<Permission> page = new Page<>(current, size);

        this.page(page, queryWrapper);

        return page;
    }

    @Override
    public List<Permission> getPermissionsByUserId(String userId) {
        return null;
    }

    @Override
    public List<Permission> getPermissionsByRoleId(String roleId) {
        List<RolePermission> rolePermissions = permissionService.list(new QueryWrapper<RolePermission>().eq("role_id", roleId));
        List<String> pIds = rolePermissions.stream().map(RolePermission::getPermissionId).collect(Collectors.toList());
        List<Permission> permissions = new ArrayList<>();

        if (pIds.size() > 0) {
            permissions = baseMapper.selectBatchIds(pIds);
        }
        return permissions;
    }

    @Override
    public void assignPermissionsForRole(String roleId, List<String> permissionIds) {

    }
}
