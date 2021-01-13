package com.atshixin.edu.controller;


import com.atshixin.edu.pojo.SubjectTreeNode;
import com.atshixin.edu.service.SubjectService;
import com.atshixin.util.R;
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
@RestController
@RequestMapping("/edu/subjects")
@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/treeNodes")
    public R getTreeNodes() {
        List<SubjectTreeNode> subjectTreeNodes = subjectService.getTreeNodes();
        return R.ok().data("items", subjectTreeNodes);
    }

    @PostMapping("/xlsx")
    public R addSubjects(@RequestParam("file") MultipartFile file) {
        subjectService.saveSubjectWithExcel(file);
        return R.ok();
    }
}

