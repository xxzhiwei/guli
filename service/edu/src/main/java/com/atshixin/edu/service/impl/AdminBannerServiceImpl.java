package com.atshixin.edu.service.impl;

import com.atshixin.edu.entity.CrmBanner;
import com.atshixin.edu.mapper.CrmBannerMapper;
import com.atshixin.edu.service.AdminBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdminBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements AdminBannerService {
    @Override
    public Page<CrmBanner> getBanners(Integer current, Integer size, QueryWrapper<CrmBanner> queryWrapper) {
        Page<CrmBanner> page = new Page<>(current, size);
        baseMapper.getBanners(page, queryWrapper);
        return page;
    }
}
