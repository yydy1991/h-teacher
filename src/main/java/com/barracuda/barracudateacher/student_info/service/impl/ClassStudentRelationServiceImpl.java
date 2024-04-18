package com.barracuda.barracudateacher.student_info.service.impl;

import com.barracuda.barracudateacher.student_info.domain.ClassStudentRelation;
import com.barracuda.barracudateacher.student_info.mapper.ClassStudentRelationMapper;
import com.barracuda.barracudateacher.student_info.service.IClassStudentRelationService;
import com.barracuda.common.core.text.Convert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 班级和学生关系Service业务层处理
 *
 * @author barracuda
 * @date 2024-03-05
 */
@Service
public class ClassStudentRelationServiceImpl implements IClassStudentRelationService {
    @Resource
    private ClassStudentRelationMapper classStudentRelationMapper;

    /**
     * 查询班级和学生关系
     *
     * @param tbStudentInfoId 班级和学生关系主键
     * @return 班级和学生关系
     */
    @Override
    public ClassStudentRelation selectClassStudentRelationByTbStudentInfoId(Long tbStudentInfoId) {
        return classStudentRelationMapper.selectClassStudentRelationByTbStudentInfoId(tbStudentInfoId);
    }

    /**
     * 查询班级和学生关系列表
     *
     * @param classStudentRelation 班级和学生关系
     * @return 班级和学生关系
     */
    @Override
    public List<ClassStudentRelation> selectClassStudentRelationList(ClassStudentRelation classStudentRelation) {
        return classStudentRelationMapper.selectClassStudentRelationList(classStudentRelation);
    }

    /**
     * 新增班级和学生关系
     *
     * @param classStudentRelation 班级和学生关系
     * @return 结果
     */
    @Override
    public int insertClassStudentRelation(ClassStudentRelation classStudentRelation) {
        return classStudentRelationMapper.insertClassStudentRelation(classStudentRelation);
    }

    /**
     * 修改班级和学生关系
     *
     * @param classStudentRelation 班级和学生关系
     * @return 结果
     */
    @Override
    public int updateClassStudentRelation(ClassStudentRelation classStudentRelation) {
        return classStudentRelationMapper.updateClassStudentRelation(classStudentRelation);
    }

    /**
     * 批量删除班级和学生关系
     *
     * @param tbStudentInfoIds 需要删除的班级和学生关系主键
     * @return 结果
     */
    @Override
    public int deleteClassStudentRelationByTbStudentInfoIds(String tbStudentInfoIds) {
        return classStudentRelationMapper.deleteClassStudentRelationByTbStudentInfoIds(Convert.toStrArray(tbStudentInfoIds));
    }

    /**
     * 删除班级和学生关系信息
     *
     * @param tbStudentInfoId 班级和学生关系主键
     * @return 结果
     */
    @Override
    public int deleteClassStudentRelationByTbStudentInfoId(Long tbStudentInfoId) {
        return classStudentRelationMapper.deleteClassStudentRelationByTbStudentInfoId(tbStudentInfoId);
    }
}
