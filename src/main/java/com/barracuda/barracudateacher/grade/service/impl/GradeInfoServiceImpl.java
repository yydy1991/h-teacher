package com.barracuda.barracudateacher.grade.service.impl;

import com.barracuda.barracudateacher.grade.domain.GradeInfo;
import com.barracuda.barracudateacher.grade.mapper.GradeInfoMapper;
import com.barracuda.barracudateacher.grade.service.IGradeInfoService;
import com.barracuda.common.core.text.Convert;
import com.barracuda.common.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 年级信息Service业务层处理
 *
 * @author barracuda
 * @date 2024-05-24
 */
@Service
public class GradeInfoServiceImpl implements IGradeInfoService {
    @Resource
    private GradeInfoMapper gradeInfoMapper;

    /**
     * 查询年级信息
     *
     * @param id 年级信息主键
     * @return 年级信息
     */
    @Override
    public GradeInfo selectGradeInfoById(Long id) {
        return gradeInfoMapper.selectGradeInfoById(id);
    }

    /**
     * 查询年级信息列表
     *
     * @param gradeInfo 年级信息
     * @return 年级信息
     */
    @Override
    public List<GradeInfo> selectGradeInfoList(GradeInfo gradeInfo) {
        return gradeInfoMapper.selectGradeInfoList(gradeInfo);
    }

    /**
     * 新增年级信息
     *
     * @param gradeInfo 年级信息
     * @return 结果
     */
    @Override
    public int insertGradeInfo(GradeInfo gradeInfo) {
        gradeInfo.setCreateTime(DateUtils.getNowDate());
        return gradeInfoMapper.insertGradeInfo(gradeInfo);
    }

    /**
     * 修改年级信息
     *
     * @param gradeInfo 年级信息
     * @return 结果
     */
    @Override
    public int updateGradeInfo(GradeInfo gradeInfo) {
        gradeInfo.setUpdateTime(DateUtils.getNowDate());
        return gradeInfoMapper.updateGradeInfo(gradeInfo);
    }

    /**
     * 批量删除年级信息
     *
     * @param ids 需要删除的年级信息主键
     * @return 结果
     */
    @Override
    public int deleteGradeInfoByIds(String ids) {
        return gradeInfoMapper.deleteGradeInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除年级信息信息
     *
     * @param id 年级信息主键
     * @return 结果
     */
    @Override
    public int deleteGradeInfoById(Long id) {
        return gradeInfoMapper.deleteGradeInfoById(id);
    }
}
