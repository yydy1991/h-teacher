package com.barracuda.barracudateacher.reference.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.barracuda.common.annotation.Excel;
import com.barracuda.common.core.domain.BaseEntity;

/**
 * 参考资料和年级信息关联对象 rt_reference_grade
 *
 * @author barracuda
 * @date 2024-05-24
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReferenceGradeRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 参考资料表ID
     */
    @Excel(name = "参考资料表ID")
    private Long referenceId;

    /**
     * 年级信息表ID
     */
    @Excel(name = "年级信息表ID")
    private Long gradeId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("referenceId", getReferenceId())
                .append("gradeId", getGradeId())
                .toString();
    }
}
