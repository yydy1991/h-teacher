package com.barracuda.barracudateacher.reference.domain;

import com.barracuda.barracudateacher.base.entity.TBaseEntity;
import com.barracuda.common.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 参考资料对象 ref_reference
 *
 * @author barracuda
 */
@Setter
@Getter
public class Reference extends TBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 资料类型
     */
    @Excel(name = "资料类型")
    private String referenceType;

    /**
     * 资料文字内容
     */
    @Excel(name = "资料文字内容")
    private String referenceContent;

    /**
     * 资料标题
     */
    @Excel(name = "资料标题")
    private String referenceTitle;

    /**
     * 资料标签
     */
    @Excel(name = "资料标签")
    private String referenceTag;

    /**
     * 学科ID
     */
    private Long subjectId;

    /**
     * 年级ID
     */
    private Long gradeId;

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
                .append("referenceType", getReferenceType())
                .append("referenceContent", getReferenceContent())
                .append("referenceTitle", getReferenceTitle())
                .append("referenceTag", getReferenceTag())
                .toString();
    }
}
