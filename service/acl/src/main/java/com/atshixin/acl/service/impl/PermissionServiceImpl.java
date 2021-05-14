package com.atshixin.acl.service.impl;

import com.atshixin.acl.entity.Permission;
import com.atshixin.acl.mapper.PermissionMapper;
import com.atshixin.acl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-05-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
