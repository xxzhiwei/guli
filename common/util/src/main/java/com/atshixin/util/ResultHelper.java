package com.atshixin.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class ResultHelper {
    public static R format(Page page) {
        return R.ok()
                .data("records", page.getRecords())
                .data("total", page.getTotal())
                .data("pages", page.getPages());
    }

    public static R format(List list) {
        return R.ok()
                .data("records", list);
    }

    public static R format(Object object) {
        return R.ok()
                .data("record", object);
    }
}
