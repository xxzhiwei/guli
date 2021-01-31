package com.atshixin.cms.controller.admin;


import com.atshixin.cms.service.SubjectService;
import com.atshixin.cms.pojo.SubjectTreeNode;
import com.atshixin.util.R;
import com.atshixin.util.ResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author atshixin
 * @since 2021-01-07
 */
@RestController("AdminSubjectController")
@RequestMapping("/cms/admin/subjects")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/treeNodes")
    public R getTreeNodes() {
        List<SubjectTreeNode> subjectTreeNodes = subjectService.getTreeNodes();
        return ResultHelper.format(subjectTreeNodes);
    }

    @PostMapping("/xlsx")
    public R addSubjects(@RequestParam("file") MultipartFile file) {
        subjectService.saveSubjectWithExcel(file);
        return R.ok();
    }
}

