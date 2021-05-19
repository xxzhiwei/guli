package com.atshixin.acl.service.impl;

import com.atshixin.acl.entity.Role;
import com.atshixin.acl.entity.UserRole;
import com.atshixin.acl.mapper.RoleMapper;
import com.atshixin.acl.service.RoleService;
import com.atshixin.acl.service.UserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public Page<Role> getRoles(Integer current, Integer size, QueryWrapper<Role> queryWrapper) {
        Page<Role> page = new Page<>(current, size);
        page(page, queryWrapper);
        return page;
    }

    @Override
    public List<Role> getRolesByUserId(String userId) {
        // 1. 查询用户的角色
        List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", userId));
        // 2. 获取角色
        List<String> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        return baseMapper.selectBatchIds(roleIds);
    }

}
