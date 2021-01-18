package com.atshixin.edu.util;

import com.atshixin.util.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class PagingHelper {
    public static R paging(Page page) {
        return R.ok()
                .data("records", page.getRecords())
                .data("total", page.getTotal())
                .data("pages", page.getPages());
    }
}
