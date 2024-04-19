package com.barracuda.barracudateacher.reference.service.impl;

import com.barracuda.barracudateacher.reference.domain.ReferenceDocumentRelation;
import com.barracuda.barracudateacher.reference.mapper.ReferenceDocumentRelationMapper;
import com.barracuda.barracudateacher.reference.service.IReferenceDocumentRelationService;
import com.barracuda.common.core.text.Convert;
import org.apache.shiro.util.Assert;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 参考资料和文档关系Service业务层处理
 *
 * @author barracuda
 */
@Service
public class ReferenceDocumentRelationServiceImpl implements IReferenceDocumentRelationService {
    @Resource
    private ReferenceDocumentRelationMapper referenceDocumentRelationMapper;

    /**
     * 查询参考资料和文档关系
     *
     * @param refReferenceId 参考资料和文档关系主键
     * @return 参考资料和文档关系
     */
    @Override
    public ReferenceDocumentRelation selectReferenceDocumentRelationByRefReferenceId(Long refReferenceId) {
        return referenceDocumentRelationMapper.selectReferenceDocumentRelationByRefReferenceId(refReferenceId);
    }

    /**
     * 查询参考资料和文档关系列表
     *
     * @param referenceDocumentRelation 参考资料和文档关系
     * @return 参考资料和文档关系
     */
    @Override
    public List<ReferenceDocumentRelation> selectReferenceDocumentRelationList(ReferenceDocumentRelation referenceDocumentRelation) {
        return referenceDocumentRelationMapper.selectReferenceDocumentRelationList(referenceDocumentRelation);
    }

    /**
     * 新增参考资料和文档关系
     *
     * @param referenceDocumentRelation 参考资料和文档关系
     * @return 结果
     */
    @Override
    public int insertReferenceDocumentRelation(ReferenceDocumentRelation referenceDocumentRelation) {
        return referenceDocumentRelationMapper.insertReferenceDocumentRelation(referenceDocumentRelation);
    }

    /**
     * 修改参考资料和文档关系
     *
     * @param referenceDocumentRelation 参考资料和文档关系
     * @return 结果
     */
    @Override
    public int updateReferenceDocumentRelation(ReferenceDocumentRelation referenceDocumentRelation) {
        return referenceDocumentRelationMapper.updateReferenceDocumentRelation(referenceDocumentRelation);
    }

    /**
     * 批量删除参考资料和文档关系
     *
     * @param refReferenceIds 需要删除的参考资料和文档关系主键
     * @return 结果
     */
    @Override
    public int deleteReferenceDocumentRelationByRefReferenceIds(String refReferenceIds) {
        return referenceDocumentRelationMapper.deleteReferenceDocumentRelationByRefReferenceIds(Convert.toStrArray(refReferenceIds));
    }

    /**
     * 删除参考资料和文档关系信息
     *
     * @param refReferenceId 参考资料和文档关系主键
     * @return 结果
     */
    @Override
    public int deleteReferenceDocumentRelationByRefReferenceId(Long refReferenceId) {
        return referenceDocumentRelationMapper.deleteReferenceDocumentRelationByRefReferenceId(refReferenceId);
    }

    @Override
    public void insertReferenceDocumentRelation(Long refReferenceId, List<Long> documentIdList) {
        Assert.notNull(refReferenceId, "refReferenceId is null");
        Assert.notNull(documentIdList, "documentIdList is null");
        for (Long documentId : documentIdList) {
            if (documentId != null) {
                ReferenceDocumentRelation relation = new ReferenceDocumentRelation(refReferenceId, documentId);
                insertReferenceDocumentRelation(relation);
            }
        }
    }
}
