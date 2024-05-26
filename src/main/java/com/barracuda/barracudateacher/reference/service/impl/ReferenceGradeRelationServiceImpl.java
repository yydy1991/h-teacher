package com.barracuda.barracudateacher.reference.service.impl;

import com.barracuda.barracudateacher.reference.domain.ReferenceGradeRelation;
import com.barracuda.barracudateacher.reference.mapper.ReferenceGradeRelationMapper;
import com.barracuda.barracudateacher.reference.service.IReferenceGradeRelationService;
import com.barracuda.common.core.text.Convert;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * 参考资料和年级信息关联Service业务层处理
 *
 * @author barracuda
 * @date 2024-05-24
 */
@Service
public class ReferenceGradeRelationServiceImpl implements IReferenceGradeRelationService {
    @Resource
    private ReferenceGradeRelationMapper referenceGradeRelationMapper;

    /**
     * 查询参考资料和年级信息关联
     *
     * @param referenceId 参考资料和年级信息关联主键
     * @return 参考资料和年级信息关联
     */
    @Override
    public ReferenceGradeRelation selectReferenceGradeRelationByReferenceId(Long referenceId) {
        return referenceGradeRelationMapper.selectReferenceGradeRelationByReferenceId(referenceId);
    }

    /**
     * 查询参考资料和年级信息关联列表
     *
     * @param referenceGradeRelation 参考资料和年级信息关联
     * @return 参考资料和年级信息关联
     */
    @Override
    public List<ReferenceGradeRelation> selectReferenceGradeRelationList(ReferenceGradeRelation referenceGradeRelation) {
        return referenceGradeRelationMapper.selectReferenceGradeRelationList(referenceGradeRelation);
    }

    /**
     * 新增参考资料和年级信息关联
     *
     * @param referenceGradeRelation 参考资料和年级信息关联
     * @return 结果
     */
    @Override
    public int insertReferenceGradeRelation(ReferenceGradeRelation referenceGradeRelation) {
        return referenceGradeRelationMapper.insertReferenceGradeRelation(referenceGradeRelation);
    }

    /**
     * 修改参考资料和年级信息关联
     *
     * @param referenceGradeRelation 参考资料和年级信息关联
     * @return 结果
     */
    @Override
    public int updateReferenceGradeRelation(ReferenceGradeRelation referenceGradeRelation) {
        return referenceGradeRelationMapper.updateReferenceGradeRelation(referenceGradeRelation);
    }

    /**
     * 批量删除参考资料和年级信息关联
     *
     * @param referenceIds 需要删除的参考资料和年级信息关联主键
     * @return 结果
     */
    @Override
    public int deleteReferenceGradeRelationByReferenceIds(String referenceIds) {
        return referenceGradeRelationMapper.deleteReferenceGradeRelationByReferenceIds(Convert.toStrArray(referenceIds));
    }

    /**
     * 删除参考资料和年级信息关联信息
     *
     * @param referenceId 参考资料和年级信息关联主键
     * @return 结果
     */
    @Override
    public int deleteReferenceGradeRelationByReferenceId(Long referenceId) {
        return referenceGradeRelationMapper.deleteReferenceGradeRelationByReferenceId(referenceId);
    }

    /**
     * 更新最新的关联关系
     *
     * @param referenceId 参考资料主键
     * @param gradeId     年级主键
     */
    @Override
    public void updateNewestRelation(Long referenceId, Long gradeId) {
        if (referenceId != null) {
            deleteReferenceGradeRelationByReferenceId(referenceId);
            if (gradeId != null) {
                ReferenceGradeRelation relation = new ReferenceGradeRelation(referenceId, gradeId);
                insertReferenceGradeRelation(relation);
            }
        }
    }

    @Override
    public List<ReferenceGradeRelation> list(Long referenceId) {
        Assert.notNull(referenceId, "referenceId is null");
        ReferenceGradeRelation relation = new ReferenceGradeRelation();
        relation.setReferenceId(referenceId);
        return selectReferenceGradeRelationList(relation);
    }
}
