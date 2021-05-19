package com.atshixin.acl.service.impl;

import com.atshixin.acl.entity.UserRole;
import com.atshixin.acl.mapper.UserRoleMapper;
import com.atshixin.acl.service.UserRoleService;
import com.atshixin.acl.vo.AssignVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-05-19
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public void assignRolesForUser(AssignVo assignVo) {
        // 1. 删除用户的所以角色
        removeUserRolesByUserId(assignVo.getUserId());

        // 2. 重新添加用户的角色
        List<UserRole> userRoles = new ArrayList<>();

        for (String roleId : assignVo.getRoleIds()) {
            UserRole userRole = new UserRole();
            userRole.setRoleId(roleId);
            userRole.setUserId(assignVo.getUserId());
            userRoles.add(userRole);
        }

        saveBatch(userRoles);
    }

    @Override
    public void removeUserRolesByUserId(String userId) {
        remove(new QueryWrapper<UserRole>().eq("user_id", userId));
    }
}
