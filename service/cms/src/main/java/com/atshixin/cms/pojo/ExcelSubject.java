package com.atshixin.cms.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelSubject {
    @ExcelProperty(index = 0)
    private String firRow;
    @ExcelProperty(index = 1)
    private String secRow;
}
