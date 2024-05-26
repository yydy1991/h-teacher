package com.barracuda.barracudateacher.subject.service.impl;

import com.barracuda.barracudateacher.subject.domain.SubjectInfo;
import com.barracuda.barracudateacher.subject.mapper.SubjectInfoMapper;
import com.barracuda.barracudateacher.subject.service.ISubjectInfoService;
import com.barracuda.common.core.text.Convert;
import com.barracuda.common.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学科信息Service业务层处理
 *
 * @author barracuda
 * @date 2024-05-24
 */
@Service("subject")
public class SubjectInfoServiceImpl implements ISubjectInfoService {
    @Resource
    private SubjectInfoMapper subjectInfoMapper;

    /**
     * 查询学科信息
     *
     * @param id 学科信息主键
     * @return 学科信息
     */
    @Override
    public SubjectInfo selectSubjectInfoById(Long id) {
        return subjectInfoMapper.selectSubjectInfoById(id);
    }

    /**
     * 查询学科信息列表
     *
     * @param subjectInfo 学科信息
     * @return 学科信息
     */
    @Override
    public List<SubjectInfo> selectSubjectInfoList(SubjectInfo subjectInfo) {
        return subjectInfoMapper.selectSubjectInfoList(subjectInfo);
    }

    /**
     * 新增学科信息
     *
     * @param subjectInfo 学科信息
     * @return 结果
     */
    @Override
    public int insertSubjectInfo(SubjectInfo subjectInfo) {
        subjectInfo.setCreateTime(DateUtils.getNowDate());
        return subjectInfoMapper.insertSubjectInfo(subjectInfo);
    }

    /**
     * 修改学科信息
     *
     * @param subjectInfo 学科信息
     * @return 结果
     */
    @Override
    public int updateSubjectInfo(SubjectInfo subjectInfo) {
        subjectInfo.setUpdateTime(DateUtils.getNowDate());
        return subjectInfoMapper.updateSubjectInfo(subjectInfo);
    }

    /**
     * 批量删除学科信息
     *
     * @param ids 需要删除的学科信息主键
     * @return 结果
     */
    @Override
    public int deleteSubjectInfoByIds(String ids) {
        return subjectInfoMapper.deleteSubjectInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学科信息信息
     *
     * @param id 学科信息主键
     * @return 结果
     */
    @Override
    public int deleteSubjectInfoById(Long id) {
        return subjectInfoMapper.deleteSubjectInfoById(id);
    }


    /**
     * thymeleaf 实现学科读取
     * subject.list()
     */
    public List<SubjectInfo> list() {
        return selectSubjectInfoList(new SubjectInfo());
    }
}
