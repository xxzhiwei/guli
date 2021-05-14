package com.atshixin.acl.service.impl;

import com.atshixin.acl.entity.User;
import com.atshixin.acl.mapper.UserMapper;
import com.atshixin.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
