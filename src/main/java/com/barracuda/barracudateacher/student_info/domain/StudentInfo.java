package com.barracuda.barracudateacher.student_info.domain;

import com.barracuda.barracudateacher.base.entity.TBaseEntity;
import com.barracuda.common.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 学生信息对象 tb_student_info
 *
 * @author barracuda
 */
@Getter
@Setter
@ToString
public class StudentInfo extends TBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 学生姓名
     */
    @Excel(name = "学生姓名")
    private String studentName;

    /**
     * 学号
     */
    @Excel(name = "学号")
    private String studentNum;

    /**
     * 标签
     */
    @Excel(name = "标签")
    private String studentTag;

    /**
     * 学生性别
     */
    @Excel(name = "学生性别", readConverterExp = "0=男,1=女,2=未知")
    private Integer studentGender;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;
}
