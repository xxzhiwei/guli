package com.atshixin.statistics.schedule;

import com.atshixin.statistics.service.DailyService;
import com.atshixin.statistics.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StatisticsTask {

    @Autowired
    private DailyService dailyService;

    // s:m:h:d:m:y
    @Scheduled(cron = "0 0 1 * * ?")
    public void dailyTask() {
        dailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
