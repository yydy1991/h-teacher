package com.barracuda.barracudateacher.student_info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.barracuda.common.annotation.Excel;
import com.barracuda.common.core.domain.BaseEntity;

/**
 * 班级和学生关系对象 rt_class_student
 * 
 * @author barracuda
 * @date 2024-03-05
 */
public class ClassStudentRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 学生信息表ID */
    @Excel(name = "学生信息表ID")
    private Long tbStudentInfoId;

    /** 班级信息表ID */
    @Excel(name = "班级信息表ID")
    private Long tbClassInfoId;

    public void setTbStudentInfoId(Long tbStudentInfoId) 
    {
        this.tbStudentInfoId = tbStudentInfoId;
    }

    public Long getTbStudentInfoId() 
    {
        return tbStudentInfoId;
    }
    public void setTbClassInfoId(Long tbClassInfoId) 
    {
        this.tbClassInfoId = tbClassInfoId;
    }

    public Long getTbClassInfoId() 
    {
        return tbClassInfoId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tbStudentInfoId", getTbStudentInfoId())
            .append("tbClassInfoId", getTbClassInfoId())
            .toString();
    }
}
