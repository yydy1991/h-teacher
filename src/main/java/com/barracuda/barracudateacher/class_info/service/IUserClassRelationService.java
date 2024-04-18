package com.barracuda.barracudateacher.class_info.service;

import java.util.List;

import com.barracuda.barracudateacher.class_info.domain.UserClassRelation;

/**
 * 教师和班级关系Service接口
 *
 * @author barracuda
 * @date 2024-03-04
 */
public interface IUserClassRelationService {
    /**
     * 查询教师和班级关系
     *
     * @param sysUserId 教师和班级关系主键
     * @return 教师和班级关系
     */
    public UserClassRelation selectUserClassRelationBySysUserId(Long sysUserId);

    /**
     * 查询教师和班级关系列表
     *
     * @param userClassRelation 教师和班级关系
     * @return 教师和班级关系集合
     */
    public List<UserClassRelation> selectUserClassRelationList(UserClassRelation userClassRelation);

    /**
     * 新增教师和班级关系
     *
     * @param userClassRelation 教师和班级关系
     * @return 结果
     */
    public int insertUserClassRelation(UserClassRelation userClassRelation);

    /**
     * 修改教师和班级关系
     *
     * @param userClassRelation 教师和班级关系
     * @return 结果
     */
    public int updateUserClassRelation(UserClassRelation userClassRelation);

    /**
     * 批量删除教师和班级关系
     *
     * @param sysUserIds 需要删除的教师和班级关系主键集合
     * @return 结果
     */
    public int deleteUserClassRelationBySysUserIds(String sysUserIds);

    /**
     * 删除教师和班级关系信息
     *
     * @param sysUserId 教师和班级关系主键
     * @return 结果
     */
    public int deleteUserClassRelationBySysUserId(Long sysUserId);

    /**
     * 新增用户和班级关系信息
     *
     * @param classInfoId 班级主键
     * @param userId      用户主键
     */
    void insertUserClassRelation(Long classInfoId, Long userId);
}
