package com.atshixin.statistics.controller;


import com.atshixin.statistics.service.DailyService;
import com.atshixin.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-05-10
 */
@RestController
@RequestMapping("/statistics/daily")
public class StatisticsController {
    @Autowired
    private DailyService dailyService;

    //统计某一天注册人数,生成统计数据
    @PostMapping("/registerCount")
    public R registerCount(@RequestParam String day) {
        dailyService.registerCount(day);
        return R.ok();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("/showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = dailyService.getShowData(type,begin,end);
        return R.ok().data(map);
    }
}

