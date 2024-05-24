package com.barracuda.barracudateacher.subject.domain;

import com.barracuda.barracudateacher.base.entity.TBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.barracuda.common.annotation.Excel;
import com.barracuda.common.core.domain.BaseEntity;

/**
 * 学科信息对象 tb_subject_info
 *
 * @author barracuda
 * @date 2024-05-24
 */
@Setter
@Getter
public class SubjectInfo extends TBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 年级名称
     */
    @Excel(name = "年级名称")
    private String subjectName;

    /**
     * 排序
     */
    @Excel(name = "排序")
    private Double sort;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("delFlag", getDelFlag())
                .append("remark", getRemark())
                .append("subjectName", getSubjectName())
                .append("sort", getSort())
                .toString();
    }
}
