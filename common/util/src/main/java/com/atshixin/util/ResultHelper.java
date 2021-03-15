package com.atshixin.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class ResultHelper {
    // 分页
    public static R format(Page page) {
        return R.ok()
                .data("records", page.getRecords())
                .data("total", page.getTotal())
                .data("pages", page.getPages())
                .data("hasNext", page.hasNext())
                .data("hasPrevious", page.hasPrevious());
    }

    // 列表
    public static R format(List list) {
        return R.ok()
                .data("records", list)
                .data("total", list.size());
    }

    // 一条数据
    public static R format(Object object) {
        return R.ok()
                .data("record", object);
    }
}
