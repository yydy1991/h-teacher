package com.barracuda.barracudateacher.reference.domain;

import com.barracuda.common.annotation.Excel;
import com.barracuda.common.core.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 参考资料和学科信息关联对象 rt_reference_subject
 *
 * @author barracuda
 * @date 2024-05-24
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceSubjectRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 参考资料表ID
     */
    @Excel(name = "参考资料表ID")
    private Long referenceId;

    /**
     * 学科信息表ID
     */
    @Excel(name = "学科信息表ID")
    private Long subjectId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("referenceId", getReferenceId())
                .append("subjectId", getSubjectId())
                .toString();
    }
}
