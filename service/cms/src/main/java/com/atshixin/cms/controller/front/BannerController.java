package com.atshixin.cms.controller.front;

import com.atshixin.cms.entity.CrmBanner;
import com.atshixin.cms.service.CrmBannerService;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cms/banners")
@CrossOrigin
public class BannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping
    public R getBanners() {
        List<CrmBanner> banners = crmBannerService.getBanners();
        return ResultHelper.format(banners);
    }
}
