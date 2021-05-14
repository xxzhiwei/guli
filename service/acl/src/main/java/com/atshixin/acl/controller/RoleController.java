package com.atshixin.acl.controller;


import com.atshixin.acl.entity.Role;
import com.atshixin.acl.service.RoleService;
import com.atshixin.acl.vo.RoleVo;
import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
@RestController
@RequestMapping("/acl/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    // 获取角色列表
    @GetMapping
    public R getRoles(
            @RequestParam(value = "current", defaultValue = "1") Integer current,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam String name) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.eq("role_name", name);
        }
        Page<Role> courses = roleService.getRoles(current, size, queryWrapper);
        return ResultHelper.format(courses);
    }
    // 创建角色
    @PostMapping
    public R createRole(@RequestBody RoleVo addRoleVo) {
        Role role = new Role();

        BeanUtils.copyProperties(addRoleVo, role);

        roleService.save(role);

        return R.ok();
    }
    // 删除角色
    @DeleteMapping("/{roleId}")
    public R deleteRoleById(@PathVariable String roleId) {
        if (StringUtils.isEmpty(roleId)) {
            throw new GuliException(2000001, "删除失败，roleId不能为空");
        }
        roleService.removeById(roleId);
        return R.ok();
    }
    // 更新角色
    @PatchMapping
    public R updateRole(@RequestBody RoleVo updateRoleVo) {
        if (StringUtils.isEmpty(updateRoleVo.getId())) {
            throw new GuliException(2000002, "更新失败，roleId不能为空");
        }
        Role role = new Role();

        BeanUtils.copyProperties(updateRoleVo, role);
        roleService.updateById(role);
        return R.ok();
    }
}

