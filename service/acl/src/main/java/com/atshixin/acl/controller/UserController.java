package com.atshixin.acl.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/acl/users")
public class UserController {
    // 获取用户列表
    public void getUsers() {}
    // 获取用户详细信息
    public void getUserById() {}
    // 创建用户
    public void createUser() {}
    // 删除用户
    public void deleteUserById() {}
    // 更新用户信息
    public void updateUser() {}
    // 为用户分配角色
    public void assignRoleForUser() {}
    // 获取用户角色
    public void getRolesByUserId() {}
}

