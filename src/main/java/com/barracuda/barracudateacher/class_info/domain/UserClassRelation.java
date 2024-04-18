package com.barracuda.barracudateacher.class_info.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.barracuda.common.annotation.Excel;
import com.barracuda.common.core.domain.BaseEntity;

/**
 * 教师和班级关系对象 rt_user_class
 *
 * @author barracuda
 * @date 2024-03-04
 */
public class UserClassRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 教师信息表ID
     */
    @Excel(name = "教师信息表ID")
    private Long sysUserId;

    /**
     * 班级信息表ID
     */
    @Excel(name = "班级信息表ID")
    private Long tbClassInfoId;

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setTbClassInfoId(Long tbClassInfoId) {
        this.tbClassInfoId = tbClassInfoId;
    }

    public Long getTbClassInfoId() {
        return tbClassInfoId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sysUserId", getSysUserId())
                .append("tbClassInfoId", getTbClassInfoId())
                .toString();
    }

    public UserClassRelation(Long sysUserId, Long tbClassInfoId) {
        this.sysUserId = sysUserId;
        this.tbClassInfoId = tbClassInfoId;
    }
}
