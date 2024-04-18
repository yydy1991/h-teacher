package com.barracuda.barracudateacher.student_info.service.impl;

import com.barracuda.barracudateacher.student_info.domain.StudentInfo;
import com.barracuda.barracudateacher.student_info.mapper.StudentInfoMapper;
import com.barracuda.barracudateacher.student_info.service.IStudentInfoService;
import com.barracuda.barracudateacher.tool.UserTool;
import com.barracuda.common.core.text.Convert;
import com.barracuda.common.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生信息Service业务层处理
 *
 * @author barracuda
 */
@Service
public class StudentInfoServiceImpl implements IStudentInfoService {
    @Resource
    private StudentInfoMapper studentInfoMapper;

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
}
