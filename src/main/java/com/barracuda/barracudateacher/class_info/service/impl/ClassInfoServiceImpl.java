package com.barracuda.barracudateacher.class_info.service.impl;

import com.barracuda.barracudateacher.class_info.domain.ClassInfo;
import com.barracuda.barracudateacher.class_info.mapper.ClassInfoMapper;
import com.barracuda.barracudateacher.class_info.service.IClassInfoService;
import com.barracuda.barracudateacher.class_info.service.IUserClassRelationService;
import com.barracuda.barracudateacher.tool.UserTool;
import com.barracuda.common.core.domain.Ztree;
import com.barracuda.common.core.domain.entity.SysUser;
import com.barracuda.common.core.text.Convert;
import com.barracuda.common.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 班级信息Service业务层处理
 *
 * @author barracuda
 * @date 2024-03-04
 */
@Service
public class ClassInfoServiceImpl implements IClassInfoService {
    @Resource
    private ClassInfoMapper classInfoMapper;

    @Resource
    private IUserClassRelationService userClassRelationService;

    /**
     * 查询班级信息
     *
     * @param id 班级信息主键
     * @return 班级信息
     */
    @Override
    public ClassInfo selectClassInfoById(Long id) {
        return classInfoMapper.selectClassInfoById(id);
    }

    /**
     * 查询班级信息列表
     *
     * @param classInfo 班级信息
     * @return 班级信息
     */
    @Override
    public List<ClassInfo> selectClassInfoList(ClassInfo classInfo) {
        return classInfoMapper.selectClassInfoList(classInfo);
    }

    /**
     * 新增班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    @Override
    public int insertClassInfo(ClassInfo classInfo) {
        classInfo.setCreateTime(DateUtils.getNowDate());
        classInfo.setCreateBy(UserTool.getCurrentUserLoginName());
        return classInfoMapper.insertClassInfo(classInfo);
    }

    /**
     * 修改班级信息
     *
     * @param classInfo 班级信息
     * @return 结果
     */
    @Override
    public int updateClassInfo(ClassInfo classInfo) {
        classInfo.setUpdateTime(DateUtils.getNowDate());
        classInfo.setUpdateBy(UserTool.getCurrentUserLoginName());
        return classInfoMapper.updateClassInfo(classInfo);
    }

    /**
     * 批量删除班级信息
     *
     * @param ids 需要删除的班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClassInfoByIds(String ids) {
        return classInfoMapper.deleteClassInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除班级信息信息
     *
     * @param id 班级信息主键
     * @return 结果
     */
    @Override
    public int deleteClassInfoById(Long id) {
        return classInfoMapper.deleteClassInfoById(id);
    }

    /**
     * 新增班级信息
     * 同时保存用户和班级的关联关系
     */
    @Override
    public int addSave(ClassInfo classInfo) {
        int i = insertClassInfo(classInfo);
        SysUser currentUser = UserTool.getCurrentUser();
        if (currentUser != null) {
            userClassRelationService.insertUserClassRelation(classInfo.getId(), currentUser.getUserId());
        }
        return i;
    }

    /**
     * 查询班级信息列表
     *
     * @param classInfo 班级信息
     * @param userId    用户主键
     * @return 班级信息集合
     */
    @Override
    public List<ClassInfo> listClassInfo(ClassInfo classInfo, Long userId) {
        List<ClassInfo> result;
        if (userId == null || UserTool.isAdmin(userId)) {
            result = selectClassInfoList(classInfo);
        } else {
            result = classInfoMapper.listClassInfo(classInfo, userId);
        }
        return result;
    }

    /**
     * 查询班级管理树
     *
     * @param classInfo 班级信息
     * @return 所有班级信息
     */
    @Override
    public List<Ztree> selectClassTree(ClassInfo classInfo) {
        SysUser currentUser = UserTool.getCurrentUser();
        Long userId;
        if (currentUser == null) {
            userId = 1L;
        } else {
            userId = currentUser.getUserId();
        }
        List<ClassInfo> classInfoList = listClassInfo(classInfo, userId);
        return initZtree(classInfoList);
    }

    private List<Ztree> initZtree(List<ClassInfo> classInfoList) {
        List<Ztree> result = new ArrayList<>();
        if (classInfoList != null) {
            for (ClassInfo classInfo : classInfoList) {
                Ztree ztree = new Ztree();
                ztree.setId(classInfo.getId());
                ztree.setpId(0L);
                ztree.setName(classInfo.getClassName());
                ztree.setTitle(classInfo.getClassName());
                if (result.isEmpty()) {
                    ztree.setChecked(true);
                }
                result.add(ztree);
            }
        }
        return result;
    }
}
