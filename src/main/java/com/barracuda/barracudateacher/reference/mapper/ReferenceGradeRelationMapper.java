package com.barracuda.barracudateacher.reference.mapper;

import java.util.List;

import com.barracuda.barracudateacher.reference.domain.ReferenceGradeRelation;

/**
 * 参考资料和年级信息关联Mapper接口
 *
 * @author barracuda
 * @date 2024-05-24
 */
public interface ReferenceGradeRelationMapper {
    /**
     * 查询参考资料和年级信息关联
     *
     * @param referenceId 参考资料和年级信息关联主键
     * @return 参考资料和年级信息关联
     */
    public ReferenceGradeRelation selectReferenceGradeRelationByReferenceId(Long referenceId);

    /**
     * 查询参考资料和年级信息关联列表
     *
     * @param referenceGradeRelation 参考资料和年级信息关联
     * @return 参考资料和年级信息关联集合
     */
    public List<ReferenceGradeRelation> selectReferenceGradeRelationList(ReferenceGradeRelation referenceGradeRelation);

    /**
     * 新增参考资料和年级信息关联
     *
     * @param referenceGradeRelation 参考资料和年级信息关联
     * @return 结果
     */
    public int insertReferenceGradeRelation(ReferenceGradeRelation referenceGradeRelation);

    /**
     * 修改参考资料和年级信息关联
     *
     * @param referenceGradeRelation 参考资料和年级信息关联
     * @return 结果
     */
    public int updateReferenceGradeRelation(ReferenceGradeRelation referenceGradeRelation);

    /**
     * 删除参考资料和年级信息关联
     *
     * @param referenceId 参考资料和年级信息关联主键
     * @return 结果
     */
    public int deleteReferenceGradeRelationByReferenceId(Long referenceId);

    /**
     * 批量删除参考资料和年级信息关联
     *
     * @param referenceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReferenceGradeRelationByReferenceIds(String[] referenceIds);
}
