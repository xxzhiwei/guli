package com.atshixin.order.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

// 订单号生成器
public class OrderNoGenerator {
    public static String generate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = dateFormat.format(new Date());
        StringBuilder randomStr = new StringBuilder();
        Random random = new Random();
        // 20210401140120698
        for (int i=0; i<3; i++) {
            randomStr.append(random.nextInt(10));
        }
        return dateStr + randomStr;
    }
}
