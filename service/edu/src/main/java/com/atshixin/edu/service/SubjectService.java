package com.atshixin.edu.service;

import com.atshixin.edu.entity.Subject;
import com.atshixin.edu.pojo.SubjectTreeNode;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author atshixin
 * @since 2021-01-07
 */
public interface SubjectService extends IService<Subject> {
    void saveSubjectWithExcel(MultipartFile file);
    List<SubjectTreeNode> getTreeNodes();
}
