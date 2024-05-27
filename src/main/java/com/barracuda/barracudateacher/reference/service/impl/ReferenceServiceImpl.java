package com.barracuda.barracudateacher.reference.service.impl;

import com.barracuda.barracudateacher.reference.domain.*;
import com.barracuda.barracudateacher.reference.mapper.ReferenceMapper;
import com.barracuda.barracudateacher.reference.service.*;
import com.barracuda.barracudateacher.tool.UserTool;
import com.barracuda.common.core.text.Convert;
import com.barracuda.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 参考资料Service业务层处理
 *
 * @author barracuda
 */
@Service
public class ReferenceServiceImpl implements IReferenceService {
    @Resource
    private ReferenceMapper referenceMapper;

    @Resource
    private IReferenceSubjectRelationService referenceSubjectRelationService;

    @Resource
    private IReferenceGradeRelationService referenceGradeRelationService;

    @Resource
    private IReferenceDocumentRelationService referenceDocumentRelationService;

    @Resource
    private IDocumentService documentService;

    /**
     * 查询参考资料
     *
     * @param id 参考资料主键
     * @return 参考资料
     */
    @Override
    public Reference selectReferenceById(Long id) {
        return referenceMapper.selectReferenceById(id);
    }

    /**
     * 查询参考资料列表
     *
     * @param reference 参考资料
     * @return 参考资料
     */
    @Override
    public List<Reference> selectReferenceList(Reference reference) {
        return referenceMapper.selectReferenceList(reference);
    }

    /**
     * 新增参考资料
     *
     * @param reference 参考资料
     * @return 结果
     */
    @Override
    public int insertReference(Reference reference) {
        reference.setCreateBy(UserTool.getCurrentUserLoginName());
        reference.setCreateTime(DateUtils.getNowDate());
        return referenceMapper.insertReference(reference);
    }

    /**
     * 修改参考资料
     *
     * @param reference 参考资料
     * @return 结果
     */
    @Override
    public int updateReference(Reference reference) {
        reference.setUpdateBy(UserTool.getCurrentUserLoginName());
        reference.setUpdateTime(DateUtils.getNowDate());
        return referenceMapper.updateReference(reference);
    }

    /**
     * 批量删除参考资料
     *
     * @param ids 需要删除的参考资料主键
     * @return 结果
     */
    @Override
    public int deleteReferenceByIds(String ids) {
        return referenceMapper.deleteReferenceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除参考资料信息
     *
     * @param id 参考资料主键
     * @return 结果
     */
    @Override
    public int deleteReferenceById(Long id) {
        return referenceMapper.deleteReferenceById(id);
    }

    @Override
    public Reference getReference(Long id) {
        return selectReferenceById(id);
    }

    @Override
    public void setProjectId(Reference reference) {
        Assert.notNull(reference, "reference is null");
        Long id = reference.getId();
        Assert.notNull(id, "reference id is null");
        List<ReferenceSubjectRelation> relations = referenceSubjectRelationService.list(id);
        if (!CollectionUtils.isEmpty(relations)) {
            Long subjectId = relations.get(0).getSubjectId();
            reference.setSubjectId(subjectId);
        }
    }

    @Override
    public void setGradeId(Reference reference) {
        Assert.notNull(reference, "reference is null");
        Long id = reference.getId();
        Assert.notNull(id, "reference id is null");
        List<ReferenceGradeRelation> relations = referenceGradeRelationService.list(id);
        if (!CollectionUtils.isEmpty(relations)) {
            Long gradeId = relations.get(0).getGradeId();
            reference.setGradeId(gradeId);
        }
    }

    /**
     * 查询所有信息
     *
     * @param reference
     */
    @Override
    public List<Reference> listAllInfo(Reference reference) {
        return referenceMapper.listAllInfo(reference);
    }

    @Override
    public void addSave(Reference reference, Long[] documentIds) {
        insertReference(reference);
        Long referenceId = reference.getId();
        if (documentIds != null && documentIds.length > 0) {
            List<Long> list = Arrays.asList(documentIds);
            referenceDocumentRelationService.insertReferenceDocumentRelation(referenceId, list);
        }
        Long subjectId = reference.getSubjectId();
        if (subjectId != null) {
            referenceSubjectRelationService.updateNewestRelation(referenceId, subjectId);
        }
        Long gradeId = reference.getGradeId();
        if (gradeId != null) {
            referenceGradeRelationService.updateNewestRelation(referenceId, gradeId);
        }
    }


    @Override
    public int editSave(Reference reference, Long[] documentIds) {
        int i = updateReference(reference);
        referenceDocumentRelationService.updateNewestRelation(reference.getId(), documentIds);
        referenceSubjectRelationService.updateNewestRelation(reference.getId(), reference.getSubjectId());
        referenceGradeRelationService.updateNewestRelation(reference.getId(), reference.getGradeId());
        return i;
    }

    /**
     * 查询附件
     *
     * @param referenceId 资料Id
     */
    @Override
    public List<Document> listDocuments(Long referenceId) {
        ReferenceDocumentRelation referenceDocumentRelation = new ReferenceDocumentRelation();
        referenceDocumentRelation.setRefReferenceId(referenceId);
        List<ReferenceDocumentRelation> referenceDocumentRelations = referenceDocumentRelationService.selectReferenceDocumentRelationList(referenceDocumentRelation);
        List<Document> documents = new ArrayList<>();
        for (ReferenceDocumentRelation documentRelation : referenceDocumentRelations) {
            Long documentId = documentRelation.getRefDocumentId();
            Document document = documentService.selectDocumentById(documentId);
            documents.add(document);
        }
        return documents;
    }
}
