package com.barracuda.barracudateacher.grade.service;

import java.util.List;
import com.barracuda.barracudateacher.grade.domain.GradeInfo;

/**
 * 年级信息Service接口
 * 
 * @author barracuda
 * @date 2024-05-24
 */
public interface IGradeInfoService 
{
    /**
     * 查询年级信息
     * 
     * @param id 年级信息主键
     * @return 年级信息
     */
    public GradeInfo selectGradeInfoById(Long id);

    /**
     * 查询年级信息列表
     * 
     * @param gradeInfo 年级信息
     * @return 年级信息集合
     */
    public List<GradeInfo> selectGradeInfoList(GradeInfo gradeInfo);

    /**
     * 新增年级信息
     * 
     * @param gradeInfo 年级信息
     * @return 结果
     */
    public int insertGradeInfo(GradeInfo gradeInfo);

    /**
     * 修改年级信息
     * 
     * @param gradeInfo 年级信息
     * @return 结果
     */
    public int updateGradeInfo(GradeInfo gradeInfo);

    /**
     * 批量删除年级信息
     * 
     * @param ids 需要删除的年级信息主键集合
     * @return 结果
     */
    public int deleteGradeInfoByIds(String ids);

    /**
     * 删除年级信息信息
     * 
     * @param id 年级信息主键
     * @return 结果
     */
    public int deleteGradeInfoById(Long id);
}
