package com.atshixin.acl.service.impl;

import com.atshixin.acl.entity.Role;
import com.atshixin.acl.mapper.RoleMapper;
import com.atshixin.acl.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<Role> getRoles(Integer current, Integer size, QueryWrapper<Role> queryWrapper) {
        Page<Role> page = new Page<>(current, size);
        page(page, queryWrapper);
        return page;
    }
}
