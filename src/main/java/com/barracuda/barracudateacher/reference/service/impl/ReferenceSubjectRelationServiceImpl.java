package com.barracuda.barracudateacher.reference.service.impl;

import com.barracuda.barracudateacher.reference.domain.ReferenceSubjectRelation;
import com.barracuda.barracudateacher.reference.mapper.ReferenceSubjectRelationMapper;
import com.barracuda.barracudateacher.reference.service.IReferenceSubjectRelationService;
import com.barracuda.common.core.text.Convert;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * 参考资料和学科信息关联Service业务层处理
 *
 * @author barracuda
 * @date 2024-05-24
 */
@Service
public class ReferenceSubjectRelationServiceImpl implements IReferenceSubjectRelationService {
    @Resource
    private ReferenceSubjectRelationMapper referenceSubjectRelationMapper;

    /**
     * 查询参考资料和学科信息关联
     *
     * @param referenceId 参考资料和学科信息关联主键
     * @return 参考资料和学科信息关联
     */
    @Override
    public ReferenceSubjectRelation selectReferenceSubjectRelationByReferenceId(Long referenceId) {
        return referenceSubjectRelationMapper.selectReferenceSubjectRelationByReferenceId(referenceId);
    }

    /**
     * 查询参考资料和学科信息关联列表
     *
     * @param referenceSubjectRelation 参考资料和学科信息关联
     * @return 参考资料和学科信息关联
     */
    @Override
    public List<ReferenceSubjectRelation> selectReferenceSubjectRelationList(ReferenceSubjectRelation referenceSubjectRelation) {
        return referenceSubjectRelationMapper.selectReferenceSubjectRelationList(referenceSubjectRelation);
    }

    /**
     * 新增参考资料和学科信息关联
     *
     * @param referenceSubjectRelation 参考资料和学科信息关联
     * @return 结果
     */
    @Override
    public int insertReferenceSubjectRelation(ReferenceSubjectRelation referenceSubjectRelation) {
        return referenceSubjectRelationMapper.insertReferenceSubjectRelation(referenceSubjectRelation);
    }

    /**
     * 修改参考资料和学科信息关联
     *
     * @param referenceSubjectRelation 参考资料和学科信息关联
     * @return 结果
     */
    @Override
    public int updateReferenceSubjectRelation(ReferenceSubjectRelation referenceSubjectRelation) {
        return referenceSubjectRelationMapper.updateReferenceSubjectRelation(referenceSubjectRelation);
    }

    /**
     * 批量删除参考资料和学科信息关联
     *
     * @param referenceIds 需要删除的参考资料和学科信息关联主键
     * @return 结果
     */
    @Override
    public int deleteReferenceSubjectRelationByReferenceIds(String referenceIds) {
        return referenceSubjectRelationMapper.deleteReferenceSubjectRelationByReferenceIds(Convert.toStrArray(referenceIds));
    }

    /**
     * 删除参考资料和学科信息关联信息
     *
     * @param referenceId 参考资料和学科信息关联主键
     * @return 结果
     */
    @Override
    public int deleteReferenceSubjectRelationByReferenceId(Long referenceId) {
        return referenceSubjectRelationMapper.deleteReferenceSubjectRelationByReferenceId(referenceId);
    }

    /**
     * 更新最新的关联关系
     *
     * @param referenceId 参考资料主键
     * @param subjectId   学科主键
     */
    @Override
    public void updateNewestRelation(Long referenceId, Long subjectId) {
        if (referenceId != null) {
            deleteReferenceSubjectRelationByReferenceId(referenceId);
            if (subjectId != null) {
                ReferenceSubjectRelation relation = new ReferenceSubjectRelation(referenceId, subjectId);
                insertReferenceSubjectRelation(relation);
            }
        }
    }

    @Override
    public List<ReferenceSubjectRelation> list(Long referenceId) {
        Assert.notNull(referenceId, "referenceId is null");
        ReferenceSubjectRelation relation = new ReferenceSubjectRelation();
        relation.setReferenceId(referenceId);
        return selectReferenceSubjectRelationList(relation);
    }
}
