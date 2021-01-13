package com.atshixin.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.edu.entity.Subject;
import com.atshixin.edu.pojo.ExcelSubject;
import com.atshixin.edu.service.SubjectService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelSubjectListener extends AnalysisEventListener<ExcelSubject> {

    private Map<String, List<Subject>> subjectMap = new HashMap<>();

    private SubjectService subjectService;

    @Override
    public void invoke(ExcelSubject excelSubject, AnalysisContext analysisContext) {
        if (excelSubject == null) {
            throw new GuliException(200001, "文件数据为空");
        }

        String firRow = excelSubject.getFirRow();
        subjectMap.computeIfAbsent(firRow, k -> new ArrayList<>());

        List<Subject> subjects = subjectMap.get(firRow);
        Subject subject = new Subject();
        subject.setTitle(excelSubject.getSecRow());
        subjects.add(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        List<Subject> doSubject = getDoSubject();
        subjectService.saveBatch(doSubject);
    }

    public ExcelSubjectListener(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    // title -> subject
    private Map<String, Subject> createSubjectMap(List<Subject> subjects) {
        Map<String, Subject> subjectMap = new HashMap<>();
        for (Subject subject : subjects) {
            subjectMap.put(subject.getTitle(), subject);
        }
        return subjectMap;
    }

    // 获取要保存到数据库的分类
    private List<Subject> getDoSubject() {
        List<Subject> subjects = subjectService.list(null);
        Map<String, Subject> doneSubjectMap = createSubjectMap(subjects);
        List<Subject> doSubject = new ArrayList<>();

        for (Map.Entry<String, List<Subject>> subjectEntry : subjectMap.entrySet()) {
            Subject parentSubject = doneSubjectMap.get(subjectEntry.getKey());
            if (parentSubject == null) {
                parentSubject = new Subject();
                parentSubject.setTitle(subjectEntry.getKey());
                parentSubject.setParentId("0");

                // 保存到数据库，（数据库的数据会映射过来吗）
                subjectService.save(parentSubject);

                // 选择相信map结构
                //for (Subject subject : subjectEntry.getValue()) {
                //    subject.setParentId(parentSubject.getParentId());
                //    doSubject.add(subject);
                //}
            }
            for (Subject subject : subjectEntry.getValue()) {
                if (doneSubjectMap.get(subject.getTitle()) == null) {
                    subject.setParentId(parentSubject.getId());
                    doSubject.add(subject);
                }
            }
        }
        return doSubject;
    }
}
