package com.atshixin.cms.service.impl;

import com.atshixin.cms.entity.CrmBanner;
import com.atshixin.cms.mapper.CrmBannerMapper;
import com.atshixin.cms.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-29
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    public Page<CrmBanner> getBanners(Integer current, Integer size, QueryWrapper<CrmBanner> queryWrapper) {
        Page<CrmBanner> page = new Page<>(current, size);
        baseMapper.getBanners(page, queryWrapper);
        return page;
    }

    @Cacheable(value = "front", key = "'banners'")
    @Override
    public List<CrmBanner> getBanners() {

        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 4");

        List<CrmBanner> banners = list(queryWrapper);
        return banners;
    }
}
