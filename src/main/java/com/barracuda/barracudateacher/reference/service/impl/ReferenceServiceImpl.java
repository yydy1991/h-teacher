package com.barracuda.barracudateacher.reference.service.impl;

import com.barracuda.barracudateacher.reference.domain.Reference;
import com.barracuda.barracudateacher.reference.mapper.ReferenceMapper;
import com.barracuda.barracudateacher.reference.service.IReferenceService;
import com.barracuda.common.core.text.Convert;
import com.barracuda.common.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
