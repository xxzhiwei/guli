package com.atshixin.cms.mapper;

import com.atshixin.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 首页banner表 Mapper 接口
 * </p>
 *
 * @author atshixin
 * @since 2021-01-29
 */
public interface CrmBannerMapper extends BaseMapper<CrmBanner> {
    IPage<CrmBanner> getBanners(IPage<CrmBanner> page, @Param(Constants.WRAPPER) Wrapper<CrmBanner> wrapper);
}
