package com.barracuda.barracudateacher.reference.mapper;

import java.util.List;
import com.barracuda.barracudateacher.reference.domain.Reference;

/**
 * 参考资料Mapper接口
 * 
 * @author barracuda
 * @date 2024-03-11
 */
public interface ReferenceMapper 
{
    /**
     * 查询参考资料
     * 
     * @param id 参考资料主键
     * @return 参考资料
     */
    public Reference selectReferenceById(Long id);

    /**
     * 查询参考资料列表
     * 
     * @param reference 参考资料
     * @return 参考资料集合
     */
    public List<Reference> selectReferenceList(Reference reference);

    /**
     * 新增参考资料
     * 
     * @param reference 参考资料
     * @return 结果
     */
    public int insertReference(Reference reference);

    /**
     * 修改参考资料
     * 
     * @param reference 参考资料
     * @return 结果
     */
    public int updateReference(Reference reference);

    /**
     * 删除参考资料
     * 
     * @param id 参考资料主键
     * @return 结果
     */
    public int deleteReferenceById(Long id);

    /**
     * 批量删除参考资料
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteReferenceByIds(String[] ids);

    List<Reference> listAllInfo(Reference reference);
}
