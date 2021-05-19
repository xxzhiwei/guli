package com.atshixin.acl.service;

import com.atshixin.acl.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
public interface UserService extends IService<User> {
    Page<User> getUsers(Integer current, Integer size, QueryWrapper<User> queryWrapper);
}
