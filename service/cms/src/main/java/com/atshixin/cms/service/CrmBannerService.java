package com.atshixin.cms.service;

import com.atshixin.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-29
 */
public interface CrmBannerService extends IService<CrmBanner> {
    Page<CrmBanner> getBanners(Integer current, Integer size, QueryWrapper<CrmBanner> queryWrapper);

    List<CrmBanner> getBanners();
}
