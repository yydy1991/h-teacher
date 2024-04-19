package com.barracuda.barracudateacher.student_info.service.impl;

import java.util.List;

import com.barracuda.barracudateacher.student_info.domain.ClassStudentRelation;
import com.barracuda.barracudateacher.student_info.service.IClassStudentRelationService;
import com.barracuda.barracudateacher.tool.UserTool;
import com.barracuda.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.barracuda.barracudateacher.student_info.mapper.StudentInfoMapper;
import com.barracuda.barracudateacher.student_info.domain.StudentInfo;
import com.barracuda.barracudateacher.student_info.service.IStudentInfoService;
import com.barracuda.common.core.text.Convert;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;

/**
 * 学生信息Service业务层处理
 *
 * @author barracuda
 */
@Slf4j
@Service
public class StudentInfoServiceImpl implements IStudentInfoService {
    @Resource
    private StudentInfoMapper studentInfoMapper;

    @Resource
    private IClassStudentRelationService classStudentRelationService;

    /**
     * 查询学生信息
     *
     * @param id 学生信息主键
     * @return 学生信息
     */
    @Override
    public StudentInfo selectStudentInfoById(Long id) {
        return studentInfoMapper.selectStudentInfoById(id);
    }

    /**
     * 查询学生信息列表
     *
     * @param studentInfo 学生信息
     * @return 学生信息
     */
    @Override
    public List<StudentInfo> selectStudentInfoList(StudentInfo studentInfo) {
        return studentInfoMapper.selectStudentInfoList(studentInfo);
    }

    /**
     * 新增学生信息
     *
     * @param studentInfo 学生信息
     * @return 结果
     */
    @Override
    public int insertStudentInfo(StudentInfo studentInfo) {
        studentInfo.setCreateTime(DateUtils.getNowDate());
        studentInfo.setCreateBy(UserTool.getCurrentUserLoginName());
        return studentInfoMapper.insertStudentInfo(studentInfo);
    }

    /**
     * 修改学生信息
     *
     * @param studentInfo 学生信息
     * @return 结果
     */
    @Override
    public int updateStudentInfo(StudentInfo studentInfo) {
        studentInfo.setUpdateTime(DateUtils.getNowDate());
        studentInfo.setUpdateBy(UserTool.getCurrentUserLoginName());
        return studentInfoMapper.updateStudentInfo(studentInfo);
    }

    /**
     * 批量删除学生信息
     *
     * @param ids 需要删除的学生信息主键
     * @return 结果
     */
    @Override
    public int deleteStudentInfoByIds(String ids) {
        return studentInfoMapper.deleteStudentInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生信息信息
     *
     * @param id 学生信息主键
     * @return 结果
     */
    @Override
    public int deleteStudentInfoById(Long id) {
        return studentInfoMapper.deleteStudentInfoById(id);
    }

    /**
     * 查询班级内的学生信息
     *
     * @param studentInfo 学生信息
     * @param classId     班级主键
     * @return 学生信息列表
     */
    @Override
    public List<StudentInfo> listStudentInfoOfClassId(StudentInfo studentInfo, Long classId) {
        return studentInfoMapper.listStudentInfoOfClassId(studentInfo, classId);
    }

    @Override
    public int insertStudentInfo(StudentInfo studentInfo, Long classId) {
        int i = insertStudentInfo(studentInfo);
        if (classId != null) {
            ClassStudentRelation relation = new ClassStudentRelation();
            relation.setTbClassInfoId(classId);
            relation.setTbStudentInfoId(studentInfo.getId());
            classStudentRelationService.insertClassStudentRelation(relation);
        }
        return i;
    }

    /**
     * 导入学生信息
     *
     * @param studentInfoList 学生信息
     * @param classId         班级主键
     * @return
     */
    @Override
    public String importStudent(List<StudentInfo> studentInfoList, Long classId) {
        Assert.notNull(classId, "没有班级信息");
        String result = "导入成功！";
        try {
            if (!CollectionUtils.isEmpty(studentInfoList)) {
                for (StudentInfo studentInfo : studentInfoList) {
                    insertStudentInfo(studentInfo, classId);
                }
            }
        } catch (Exception e) {
            result = "导入失败！";
            log.error("导入失败。" + e.getMessage(), e);
        }

        return result;
    }
}
