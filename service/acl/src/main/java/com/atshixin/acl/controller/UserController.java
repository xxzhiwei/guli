package com.atshixin.acl.controller;


import com.atshixin.acl.entity.Role;
import com.atshixin.acl.entity.User;
import com.atshixin.acl.service.RoleService;
import com.atshixin.acl.service.UserRoleService;
import com.atshixin.acl.service.UserService;
import com.atshixin.acl.vo.AssignVo;
import com.atshixin.acl.vo.UserVo;
import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;

    // 获取用户列表
    @GetMapping
    public R getUsers(
            @RequestParam(value = "current", defaultValue = "1") Integer current,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(required = false) String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("username", name);
        }
        Page<User> userPage = userService.getUsers(current, size, queryWrapper);
        return ResultHelper.format(userPage);
    }
    // 获取用户详细信息
    @GetMapping("/{userId}")
    public R getUserById(@PathVariable String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new GuliException(200008, "userId不能为空");
        }
        User user = userService.getById(userId);
        return ResultHelper.format(user);
    }
    // 创建用户
    @PostMapping
    public R createUser(@RequestBody UserVo addUserVo) {

        User user = new User();
        BeanUtils.copyProperties(addUserVo, user);

        userService.save(user);

        return R.ok();
    }
    // 删除用户
    @DeleteMapping("/{userId}")
    public R deleteUserById(@PathVariable String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new GuliException(2000011, "删除失败，userId不能为空");
        }
        // 1. 删除用户
        userService.removeById(userId);
        // 2. 删除用户已分配的角色
        userRoleService.removeUserRolesByUserId(userId);
        return R.ok();
    }
    // 更新用户信息
    @PutMapping
    public R updateUser(@RequestBody UserVo updateUserVo) {
        if (StringUtils.isEmpty(updateUserVo.getId())) {
            throw new GuliException(2000012, "更新失败，userId不能为空");
        }

        User user = new User();

        BeanUtils.copyProperties(updateUserVo, user);
        userService.updateById(user);
        return R.ok();
    }
    // 为用户分配角色
    @PostMapping("/assign")
    public R assignRoleForUser(@RequestBody AssignVo assignVo) {
        if (StringUtils.isEmpty(assignVo.getUserId()) || assignVo.getRoleIds().size() == 0) {
            throw new GuliException(2000012, "userId和roleIds不能为空");
        }

        userRoleService.assignRolesForUser(assignVo);
        return R.ok();
    }
    // 获取用户角色
    @GetMapping("/{userId}/roles")
    public R getRolesByUserId(@PathVariable String userId) {
        if (StringUtils.isEmpty(userId)) {
            throw new GuliException(20000123, "userId不能为空");
        }
        List<Role> roles = roleService.getRolesByUserId(userId);
        return ResultHelper.format(roles);
    }
}

