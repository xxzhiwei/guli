package com.atshixin.edu.service;

import com.atshixin.edu.entity.CrmBanner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminBannerService extends IService<CrmBanner> {
    Page<CrmBanner> getBanners(Integer current, Integer size, QueryWrapper<CrmBanner> queryWrapper);
}
