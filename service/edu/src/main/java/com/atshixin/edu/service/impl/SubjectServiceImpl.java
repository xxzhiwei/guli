package com.atshixin.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atshixin.base.exceptionHandler.GuliException;
import com.atshixin.edu.entity.Subject;
import com.atshixin.edu.listener.ExcelSubjectListener;
import com.atshixin.edu.mapper.SubjectMapper;
import com.atshixin.edu.pojo.ExcelSubject;
import com.atshixin.edu.pojo.SubjectTreeNode;
import com.atshixin.edu.service.SubjectService;
import com.atshixin.util.ResultCode;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-07
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Override
    public void saveSubjectWithExcel(MultipartFile file) {
        try {
            InputStream fileInputStream = file.getInputStream();
            EasyExcel.read(
                    fileInputStream,
                    ExcelSubject.class /* excel中的每行 */,
                    new ExcelSubjectListener(this)
            ).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuliException(ResultCode.ERROR, "保存excel数据发生错误");
        }
    }

    @Override
    public List<SubjectTreeNode> getTreeNodes() {
        List<Subject> subjects = list(null);
        List<SubjectTreeNode> subjectTreeNodes = new ArrayList<>();

        for (int i = 0; i < subjects.size(); i++) {

                Subject subject = subjects.get(i);

                if (subject.getParentId().equals("0")) {

                    SubjectTreeNode parentSubjectTreeNode = new SubjectTreeNode();
                    List<SubjectTreeNode> children = new ArrayList<>();
                    BeanUtils.copyProperties(subject, parentSubjectTreeNode);

                    for (Subject subSubject : subjects) {
                        if (subSubject.getParentId().equals(subject.getId())) {
                            SubjectTreeNode subjectTreeNode = new SubjectTreeNode();
                            BeanUtils.copyProperties(subSubject, subjectTreeNode);
                            children.add(subjectTreeNode);
                        }
                    }
                    parentSubjectTreeNode.setChildren(children);
                    subjectTreeNodes.add(parentSubjectTreeNode);
                }
        }
        return subjectTreeNodes;
    }
}
