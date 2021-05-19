package com.atshixin.acl.service;

import com.atshixin.acl.entity.UserRole;
import com.atshixin.acl.vo.AssignVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-05-19
 */
public interface UserRoleService extends IService<UserRole> {
    void assignRolesForUser(AssignVo assignVo);
    void removeUserRolesByUserId(String userId);
}
