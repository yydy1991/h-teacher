package com.barracuda.barracudateacher.base.entity;

import com.barracuda.common.core.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TBaseEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 删除标识 0:未删除 1:已删除
     */
    private Integer delFlag;
}
