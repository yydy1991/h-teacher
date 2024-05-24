package com.barracuda.barracudateacher.subject.controller;

import com.barracuda.barracudateacher.subject.domain.SubjectInfo;
import com.barracuda.barracudateacher.subject.service.ISubjectInfoService;
import com.barracuda.common.annotation.Log;
import com.barracuda.common.core.controller.BaseController;
import com.barracuda.common.core.domain.AjaxResult;
import com.barracuda.common.core.page.TableDataInfo;
import com.barracuda.common.enums.BusinessType;
import com.barracuda.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学科信息Controller
 *
 * @author barracuda
 * @date 2024-05-24
 */
@Controller
@RequestMapping("/subject/subject_info")
public class SubjectInfoController extends BaseController {
    private String prefix = "subject/subject_info";

    @Resource
    private ISubjectInfoService subjectInfoService;

    @RequiresPermissions("subject:subject_info:view")
    @GetMapping()
    public String subjectInfo() {
        return prefix + "/subject_info";
    }

    /**
     * 查询学科信息列表
     */
    @RequiresPermissions("subject:subject_info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SubjectInfo subjectInfo) {
        startPage();
        List<SubjectInfo> list = subjectInfoService.selectSubjectInfoList(subjectInfo);
        return getDataTable(list);
    }

    /**
     * 导出学科信息列表
     */
    @RequiresPermissions("subject:subject_info:export")
    @Log(title = "学科信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SubjectInfo subjectInfo) {
        List<SubjectInfo> list = subjectInfoService.selectSubjectInfoList(subjectInfo);
        ExcelUtil<SubjectInfo> util = new ExcelUtil<>(SubjectInfo.class);
        return util.exportExcel(list, "学科信息数据");
    }

    /**
     * 新增学科信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存学科信息
     */
    @RequiresPermissions("subject:subject_info:add")
    @Log(title = "学科信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SubjectInfo subjectInfo) {
        return toAjax(subjectInfoService.insertSubjectInfo(subjectInfo));
    }

    /**
     * 修改学科信息
     */
    @RequiresPermissions("subject:subject_info:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SubjectInfo subjectInfo = subjectInfoService.selectSubjectInfoById(id);
        mmap.put("subjectInfo", subjectInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存学科信息
     */
    @RequiresPermissions("subject:subject_info:edit")
    @Log(title = "学科信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SubjectInfo subjectInfo) {
        return toAjax(subjectInfoService.updateSubjectInfo(subjectInfo));
    }

    /**
     * 删除学科信息
     */
    @RequiresPermissions("subject:subject_info:remove")
    @Log(title = "学科信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(subjectInfoService.deleteSubjectInfoByIds(ids));
    }
}
