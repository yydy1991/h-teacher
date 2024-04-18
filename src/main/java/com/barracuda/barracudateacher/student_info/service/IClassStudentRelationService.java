package com.barracuda.barracudateacher.student_info.service;

import java.util.List;
import com.barracuda.barracudateacher.student_info.domain.ClassStudentRelation;

/**
 * 班级和学生关系Service接口
 * 
 * @author barracuda
 * @date 2024-03-05
 */
public interface IClassStudentRelationService 
{
    /**
     * 查询班级和学生关系
     * 
     * @param tbStudentInfoId 班级和学生关系主键
     * @return 班级和学生关系
     */
    public ClassStudentRelation selectClassStudentRelationByTbStudentInfoId(Long tbStudentInfoId);

    /**
     * 查询班级和学生关系列表
     * 
     * @param classStudentRelation 班级和学生关系
     * @return 班级和学生关系集合
     */
    public List<ClassStudentRelation> selectClassStudentRelationList(ClassStudentRelation classStudentRelation);

    /**
     * 新增班级和学生关系
     * 
     * @param classStudentRelation 班级和学生关系
     * @return 结果
     */
    public int insertClassStudentRelation(ClassStudentRelation classStudentRelation);

    /**
     * 修改班级和学生关系
     * 
     * @param classStudentRelation 班级和学生关系
     * @return 结果
     */
    public int updateClassStudentRelation(ClassStudentRelation classStudentRelation);

    /**
     * 批量删除班级和学生关系
     * 
     * @param tbStudentInfoIds 需要删除的班级和学生关系主键集合
     * @return 结果
     */
    public int deleteClassStudentRelationByTbStudentInfoIds(String tbStudentInfoIds);

    /**
     * 删除班级和学生关系信息
     * 
     * @param tbStudentInfoId 班级和学生关系主键
     * @return 结果
     */
    public int deleteClassStudentRelationByTbStudentInfoId(Long tbStudentInfoId);
}
