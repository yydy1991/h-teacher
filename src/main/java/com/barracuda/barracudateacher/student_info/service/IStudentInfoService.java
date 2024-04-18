package com.barracuda.barracudateacher.student_info.service;

import java.util.List;
import com.barracuda.barracudateacher.student_info.domain.StudentInfo;

/**
 * 学生信息Service接口
 * 
 * @author barracuda
 * @date 2024-03-05
 */
public interface IStudentInfoService 
{
    /**
     * 查询学生信息
     * 
     * @param id 学生信息主键
     * @return 学生信息
     */
    public StudentInfo selectStudentInfoById(Long id);

    /**
     * 查询学生信息列表
     * 
     * @param studentInfo 学生信息
     * @return 学生信息集合
     */
    public List<StudentInfo> selectStudentInfoList(StudentInfo studentInfo);

    /**
     * 新增学生信息
     * 
     * @param studentInfo 学生信息
     * @return 结果
     */
    public int insertStudentInfo(StudentInfo studentInfo);

    /**
     * 修改学生信息
     * 
     * @param studentInfo 学生信息
     * @return 结果
     */
    public int updateStudentInfo(StudentInfo studentInfo);

    /**
     * 批量删除学生信息
     * 
     * @param ids 需要删除的学生信息主键集合
     * @return 结果
     */
    public int deleteStudentInfoByIds(String ids);

    /**
     * 删除学生信息信息
     * 
     * @param id 学生信息主键
     * @return 结果
     */
    public int deleteStudentInfoById(Long id);
}
