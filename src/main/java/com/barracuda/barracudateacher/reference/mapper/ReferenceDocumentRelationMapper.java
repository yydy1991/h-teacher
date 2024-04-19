package com.barracuda.barracudateacher.reference.mapper;

import java.util.List;

import com.barracuda.barracudateacher.reference.domain.ReferenceDocumentRelation;

/**
 * 参考资料和文档关系Mapper接口
 *
 * @author barracuda
 * @date 2024-03-11
 */
public interface ReferenceDocumentRelationMapper {
    /**
     * 查询参考资料和文档关系
     *
     * @param refReferenceId 参考资料和文档关系主键
     * @return 参考资料和文档关系
     */
    public ReferenceDocumentRelation selectReferenceDocumentRelationByRefReferenceId(Long refReferenceId);

    /**
     * 查询参考资料和文档关系列表
     *
     * @param referenceDocumentRelation 参考资料和文档关系
     * @return 参考资料和文档关系集合
     */
    public List<ReferenceDocumentRelation> selectReferenceDocumentRelationList(ReferenceDocumentRelation referenceDocumentRelation);

    /**
     * 新增参考资料和文档关系
     *
     * @param referenceDocumentRelation 参考资料和文档关系
     * @return 结果
     */
    public int insertReferenceDocumentRelation(ReferenceDocumentRelation referenceDocumentRelation);

    /**
     * 修改参考资料和文档关系
     *
     * @param referenceDocumentRelation 参考资料和文档关系
     * @return 结果
     */
    public int updateReferenceDocumentRelation(ReferenceDocumentRelation referenceDocumentRelation);

    /**
     * 删除参考资料和文档关系
     *
     * @param refReferenceId 参考资料和文档关系主键
     * @return 结果
     */
    public int deleteReferenceDocumentRelationByRefReferenceId(Long refReferenceId);

    /**
     * 批量删除参考资料和文档关系
     *
     * @param refReferenceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReferenceDocumentRelationByRefReferenceIds(String[] refReferenceIds);
}
