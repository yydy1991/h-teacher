package com.barracuda.barracudateacher.class_info.mapper;

import java.util.List;

import com.barracuda.barracudateacher.class_info.domain.ClassInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 班级信息Mapper接口
 *
 * @author barracuda
 * @date 2024-03-04
 */
public interface ClassInfoMapper {
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
     * 删除班级信息
     *
     * @param id 班级信息主键
     * @return 结果
     */
    public int deleteClassInfoById(Long id);

    /**
     * 批量删除班级信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClassInfoByIds(String[] ids);

    /**
     * 查询班级信息列表
     *
     * @param classInfo 班级信息
     * @param userId    用户主键
     * @return 班级信息集合
     */
    List<ClassInfo> listClassInfo(@Param("classInfo") ClassInfo classInfo, @Param("userId") Long userId);
}
