package com.barracuda.barracudateacher.reference.domain;

import com.barracuda.barracudateacher.base.entity.TBaseEntity;
import com.barracuda.common.annotation.Excel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文档对象 ref_document
 *
 * @author barracuda
 */
@Setter
@Getter
public class Document extends TBaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;


    /**
     * 文档名称
     */
    @Excel(name = "文档名称")
    private String documentName;
    /**
     * 文档类型
     */
    @Excel(name = "文档类型")
    private String documentType;

    /**
     * 文档大小（K）
     */
    @Excel(name = "文档大小", readConverterExp = "K=")
    private Long documentSize;

    /**
     * 文档地址
     */
    @Excel(name = "文档地址")
    private String documentAddress;


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
                .append("documentType", getDocumentType())
                .append("documentSize", getDocumentSize())
                .append("documentAddress", getDocumentAddress())
                .toString();
    }
}
