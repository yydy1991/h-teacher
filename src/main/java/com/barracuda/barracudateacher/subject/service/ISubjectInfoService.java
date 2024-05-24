package com.barracuda.barracudateacher.subject.service;

import java.util.List;
import com.barracuda.barracudateacher.subject.domain.SubjectInfo;

/**
 * 学科信息Service接口
 * 
 * @author barracuda
 * @date 2024-05-24
 */
public interface ISubjectInfoService 
{
    /**
     * 查询学科信息
     * 
     * @param id 学科信息主键
     * @return 学科信息
     */
    public SubjectInfo selectSubjectInfoById(Long id);

    /**
     * 查询学科信息列表
     * 
     * @param subjectInfo 学科信息
     * @return 学科信息集合
     */
    public List<SubjectInfo> selectSubjectInfoList(SubjectInfo subjectInfo);

    /**
     * 新增学科信息
     * 
     * @param subjectInfo 学科信息
     * @return 结果
     */
    public int insertSubjectInfo(SubjectInfo subjectInfo);

    /**
     * 修改学科信息
     * 
     * @param subjectInfo 学科信息
     * @return 结果
     */
    public int updateSubjectInfo(SubjectInfo subjectInfo);

    /**
     * 批量删除学科信息
     * 
     * @param ids 需要删除的学科信息主键集合
     * @return 结果
     */
    public int deleteSubjectInfoByIds(String ids);

    /**
     * 删除学科信息信息
     * 
     * @param id 学科信息主键
     * @return 结果
     */
    public int deleteSubjectInfoById(Long id);
}
