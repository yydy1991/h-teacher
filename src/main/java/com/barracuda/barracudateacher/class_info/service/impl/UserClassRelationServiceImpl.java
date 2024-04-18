package com.barracuda.barracudateacher.class_info.service.impl;

import com.barracuda.barracudateacher.class_info.domain.UserClassRelation;
import com.barracuda.barracudateacher.class_info.mapper.UserClassRelationMapper;
import com.barracuda.barracudateacher.class_info.service.IUserClassRelationService;
import com.barracuda.common.core.text.Convert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师和班级关系Service业务层处理
 *
 * @author barracuda
 */
@Service
@Slf4j
public class UserClassRelationServiceImpl implements IUserClassRelationService {
    @Resource
    private UserClassRelationMapper userClassRelationMapper;

    /**
     * 查询教师和班级关系
     *
     * @param sysUserId 教师和班级关系主键
     * @return 教师和班级关系
     */
    @Override
    public UserClassRelation selectUserClassRelationBySysUserId(Long sysUserId) {
        return userClassRelationMapper.selectUserClassRelationBySysUserId(sysUserId);
    }

    /**
     * 查询教师和班级关系列表
     *
     * @param userClassRelation 教师和班级关系
     * @return 教师和班级关系
     */
    @Override
    public List<UserClassRelation> selectUserClassRelationList(UserClassRelation userClassRelation) {
        return userClassRelationMapper.selectUserClassRelationList(userClassRelation);
    }

    /**
     * 新增教师和班级关系
     *
     * @param userClassRelation 教师和班级关系
     * @return 结果
     */
    @Override
    public int insertUserClassRelation(UserClassRelation userClassRelation) {
        return userClassRelationMapper.insertUserClassRelation(userClassRelation);
    }

    /**
     * 修改教师和班级关系
     *
     * @param userClassRelation 教师和班级关系
     * @return 结果
     */
    @Override
    public int updateUserClassRelation(UserClassRelation userClassRelation) {
        return userClassRelationMapper.updateUserClassRelation(userClassRelation);
    }

    /**
     * 批量删除教师和班级关系
     *
     * @param sysUserIds 需要删除的教师和班级关系主键
     * @return 结果
     */
    @Override
    public int deleteUserClassRelationBySysUserIds(String sysUserIds) {
        return userClassRelationMapper.deleteUserClassRelationBySysUserIds(Convert.toStrArray(sysUserIds));
    }

    /**
     * 删除教师和班级关系信息
     *
     * @param sysUserId 教师和班级关系主键
     * @return 结果
     */
    @Override
    public int deleteUserClassRelationBySysUserId(Long sysUserId) {
        return userClassRelationMapper.deleteUserClassRelationBySysUserId(sysUserId);
    }

    /**
     * 新增用户和班级关系信息
     *
     * @param classInfoId 班级主键
     * @param userId      用户主键
     */
    @Override
    public void insertUserClassRelation(Long classInfoId, Long userId) {
        UserClassRelation re = new UserClassRelation(userId, classInfoId);
        insertUserClassRelation(re);
        log.debug("insertUserClassRelation success.{}", re);
    }
}
