package com.barracuda.barracudateacher.reference.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.barracuda.common.annotation.Excel;
import com.barracuda.common.core.domain.BaseEntity;

/**
 * 参考资料和文档关系对象 rt_reference_document
 *
 * @author barracuda
 */
@Setter
@Getter
public class ReferenceDocumentRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 参考资料表ID
     */
    @Excel(name = "参考资料表ID")
    private Long refReferenceId;

    /**
     * 文档表ID
     */
    @Excel(name = "文档表ID")
    private Long refDocumentId;

    public ReferenceDocumentRelation(Long refReferenceId, Long refDocumentId) {
        this.refReferenceId = refReferenceId;
        this.refDocumentId = refDocumentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("refReferenceId", getRefReferenceId())
                .append("refDocumentId", getRefDocumentId())
                .toString();
    }
}
