package com.atshixin.edu.controller.admin;

import com.atshixin.edu.common.PagingDefaultParameters;
import com.atshixin.edu.entity.CrmBanner;
import com.atshixin.edu.service.AdminBannerService;
import com.atshixin.edu.vo.Banner;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController(value = "AdminBannerController")
@RequestMapping("/edu/admin/banners")
public class BannerController {

    @Autowired
    private AdminBannerService adminBannerService;

    @GetMapping
    public R getBanners(
            @RequestParam(value = "current", defaultValue = PagingDefaultParameters.CURRENT) Integer current, @RequestParam(value = "size", defaultValue = PagingDefaultParameters.SIZE) Integer size,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "begin", required = false) String begin, @RequestParam(value = "end", required = false) String end
    ) {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();

        queryWrapper.ne("is_deleted", 1);

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }


        Page<CrmBanner> page = adminBannerService.getBanners(current, size, queryWrapper);
        return ResultHelper.format(page);
    }

    @PostMapping
    public R addBanner(@RequestBody Banner bannerVo) {

        CrmBanner banner = new CrmBanner();

        BeanUtils.copyProperties(bannerVo, banner);

        boolean isOK = adminBannerService.save(banner);

        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @PutMapping("{id}")
    public R updateBanner(@PathVariable("id") String bannerId, @RequestBody Banner bannerVo) {

        if (StringUtils.isEmpty(bannerVo.getId())) {
            bannerVo.setId(bannerId);
        }
        CrmBanner banner = new CrmBanner();

        BeanUtils.copyProperties(bannerVo, banner);

        boolean isOK = adminBannerService.updateById(banner);

        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }

    @DeleteMapping("{id}")
    public R deleteBannerById(@PathVariable("id") String bannerId) {

        boolean isOK = adminBannerService.removeById(bannerId);

        if (isOK) {
            return R.ok();
        }
        else {
            return R.error();
        }
    }
}
