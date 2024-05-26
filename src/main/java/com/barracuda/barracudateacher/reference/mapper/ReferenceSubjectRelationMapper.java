package com.barracuda.barracudateacher.reference.mapper;

import com.barracuda.barracudateacher.reference.domain.ReferenceSubjectRelation;

import java.util.List;

/**
 * 参考资料和学科信息关联Mapper接口
 *
 * @author barracuda
 * @date 2024-05-24
 */
public interface ReferenceSubjectRelationMapper {
    /**
     * 查询参考资料和学科信息关联
     *
     * @param referenceId 参考资料和学科信息关联主键
     * @return 参考资料和学科信息关联
     */
    public ReferenceSubjectRelation selectReferenceSubjectRelationByReferenceId(Long referenceId);

    /**
     * 查询参考资料和学科信息关联列表
     *
     * @param referenceSubjectRelation 参考资料和学科信息关联
     * @return 参考资料和学科信息关联集合
     */
    public List<ReferenceSubjectRelation> selectReferenceSubjectRelationList(ReferenceSubjectRelation referenceSubjectRelation);

    /**
     * 新增参考资料和学科信息关联
     *
     * @param referenceSubjectRelation 参考资料和学科信息关联
     * @return 结果
     */
    public int insertReferenceSubjectRelation(ReferenceSubjectRelation referenceSubjectRelation);

    /**
     * 修改参考资料和学科信息关联
     *
     * @param referenceSubjectRelation 参考资料和学科信息关联
     * @return 结果
     */
    public int updateReferenceSubjectRelation(ReferenceSubjectRelation referenceSubjectRelation);

    /**
     * 删除参考资料和学科信息关联
     *
     * @param referenceId 参考资料和学科信息关联主键
     * @return 结果
     */
    public int deleteReferenceSubjectRelationByReferenceId(Long referenceId);

    /**
     * 批量删除参考资料和学科信息关联
     *
     * @param referenceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReferenceSubjectRelationByReferenceIds(String[] referenceIds);
}
