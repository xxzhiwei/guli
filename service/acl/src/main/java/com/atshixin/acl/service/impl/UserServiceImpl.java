package com.atshixin.acl.service.impl;

import com.atshixin.acl.entity.User;
import com.atshixin.acl.entity.UserRole;
import com.atshixin.acl.mapper.UserMapper;
import com.atshixin.acl.service.RoleService;
import com.atshixin.acl.service.UserRoleService;
import com.atshixin.acl.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Page<User> getUsers(Integer current, Integer size, QueryWrapper<User> queryWrapper) {
        Page<User> page = new Page<>();
        this.page(page, queryWrapper);
        return page;
    }
}
