package com.barracuda.barracudateacher.reference.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 资料对象初始化
 *
 * @author barracuda
 */
@Setter
@Getter
public class ReferenceInit {

    /**
     * 文件目录
     */
    private String fileMenu;

    /**
     * 学科ID
     */
    private Long subjectId;

    /**
     * 年级ID
     */
    private Long gradeId;

}
