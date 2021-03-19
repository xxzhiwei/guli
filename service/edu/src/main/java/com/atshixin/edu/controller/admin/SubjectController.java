package com.atshixin.edu.controller.admin;


import com.atshixin.edu.service.SubjectService;
import com.atshixin.edu.vo.SubjectTreeNode;
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
@RequestMapping("/edu/admin/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/treeNodes")
    public R getTreeNodes() {
        List<SubjectTreeNode> subjectTreeNodes = subjectService.getTreeNodes();
        return ResultHelper.format(subjectTreeNodes);
    }

    @PostMapping("/upload/xlsx")
    public R addSubjects(@RequestParam("file") MultipartFile file) {
        subjectService.saveSubjectWithExcel(file);
        return R.ok();
    }
}

