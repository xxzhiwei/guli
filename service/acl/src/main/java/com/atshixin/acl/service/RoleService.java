package com.atshixin.acl.service;

import com.atshixin.acl.entity.Role;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
public interface RoleService extends IService<Role> {
    Page<Role> getRoles(Integer current, Integer size, QueryWrapper<Role> queryWrapper);
}
