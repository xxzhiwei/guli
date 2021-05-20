package com.atshixin.acl.service.impl;

import com.atshixin.acl.entity.Permission;
import com.atshixin.acl.entity.RolePermission;
import com.atshixin.acl.mapper.PermissionMapper;
import com.atshixin.acl.service.PermissionService;
import com.atshixin.acl.service.RolePermissionService;
import com.atshixin.acl.vo.SamplePermissionTree;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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
    public List<SamplePermissionTree> getPermissionTrees() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        List<Permission> permissions = list(queryWrapper);
        return createPermissionTrees(permissions);
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

    private static List<SamplePermissionTree> createPermissionTrees(List<Permission> permissions) {
        List<SamplePermissionTree> permissionTrees = new ArrayList<>();
        for (Permission permission : permissions) {
            if (permission.getParentId().equals("")) {
                SamplePermissionTree parentNode = new SamplePermissionTree();
                BeanUtils.copyProperties(permission, parentNode);
                permissionTrees.add(setChildren(parentNode, permissions));
            }
        }
        return permissionTrees;
    }

    private static SamplePermissionTree setChildren(SamplePermissionTree parentNode, List<Permission> permissions) {

        for (Permission permission : permissions) {
            if (permission.getParentId().equals(parentNode.getId())) {
                SamplePermissionTree node = new SamplePermissionTree();
                BeanUtils.copyProperties(permission, node);
                parentNode.getChildren().add(setChildren(node, permissions));
            }
        }
        return parentNode;
    }
}
