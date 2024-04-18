package com.barracuda.barracudateacher.class_info.service;

import java.util.List;

import com.barracuda.barracudateacher.class_info.domain.ClassInfo;
import com.barracuda.common.core.domain.Ztree;

/**
 * 班级信息Service接口
 *
 * @author barracuda
 * @date 2024-03-04
 */
public interface IClassInfoService {
    /**
     * 查询班级信息
     *
     * @param id 班级信息主键
     * @return 班级信息
     */
    public ClassInfo selectClassInfoById(Long id);

    /**
     * 查询班级信息列表
     *
     * @param classInfo 班级信息
     * @return 班级信息集合
     */
    public List<ClassInfo> selectClassInfoList(ClassInfo classInfo);

    /**
     * 新增班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    public int insertClassInfo(ClassInfo classInfo);

    /**
     * 修改班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    public int updateClassInfo(ClassInfo classInfo);

    /**
     * 批量删除班级信息
     *
     * @param ids 需要删除的班级信息主键集合
     * @return 结果
     */
    public int deleteClassInfoByIds(String ids);

    /**
     * 删除班级信息信息
     *
     * @param id 班级信息主键
     * @return 结果
     */
    public int deleteClassInfoById(Long id);

    /**
     * 新增班级信息
     * 同时保存用户和班级的关联关系
     */
    int addSave(ClassInfo classInfo);

    /**
     * 查询班级信息列表
     *
     * @param classInfo 班级信息
     * @param userId    用户主键
     * @return 班级信息集合
     */
    List<ClassInfo> listClassInfo(ClassInfo classInfo, Long userId);

    /**
     * 查询班级管理树
     *
     * @param classInfo 班级信息
     * @return 所有班级信息
     */
    List<Ztree> selectClassTree(ClassInfo classInfo);
}
