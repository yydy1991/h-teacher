package com.barracuda.barracudateacher.student_info.controller;

import com.barracuda.barracudateacher.student_info.domain.ClassStudentRelation;
import com.barracuda.barracudateacher.student_info.service.IClassStudentRelationService;
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
 * 班级和学生关系Controller
 *
 * @author barracuda
 * @date 2024-03-05
 */
@Controller
@RequestMapping("/studentclassrelation/studentclassrelation")
public class ClassStudentRelationController extends BaseController {
    private String prefix = "studentclassrelation/studentclassrelation";

    @Resource
    private IClassStudentRelationService classStudentRelationService;

    @RequiresPermissions("studentclassrelation:studentclassrelation:view")
    @GetMapping()
    public String studentclassrelation() {
        return prefix + "/studentclassrelation";
    }

    /**
     * 查询班级和学生关系列表
     */
    @RequiresPermissions("studentclassrelation:studentclassrelation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ClassStudentRelation classStudentRelation) {
        startPage();
        List<ClassStudentRelation> list = classStudentRelationService.selectClassStudentRelationList(classStudentRelation);
        return getDataTable(list);
    }

    /**
     * 导出班级和学生关系列表
     */
    @RequiresPermissions("studentclassrelation:studentclassrelation:export")
    @Log(title = "班级和学生关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ClassStudentRelation classStudentRelation) {
        List<ClassStudentRelation> list = classStudentRelationService.selectClassStudentRelationList(classStudentRelation);
        ExcelUtil<ClassStudentRelation> util = new ExcelUtil<>(ClassStudentRelation.class);
        return util.exportExcel(list, "班级和学生关系数据");
    }

    /**
     * 新增班级和学生关系
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存班级和学生关系
     */
    @RequiresPermissions("studentclassrelation:studentclassrelation:add")
    @Log(title = "班级和学生关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ClassStudentRelation classStudentRelation) {
        return toAjax(classStudentRelationService.insertClassStudentRelation(classStudentRelation));
    }

    /**
     * 修改班级和学生关系
     */
    @RequiresPermissions("studentclassrelation:studentclassrelation:edit")
    @GetMapping("/edit/{tbStudentInfoId}")
    public String edit(@PathVariable("tbStudentInfoId") Long tbStudentInfoId, ModelMap mmap) {
        ClassStudentRelation classStudentRelation = classStudentRelationService.selectClassStudentRelationByTbStudentInfoId(tbStudentInfoId);
        mmap.put("classStudentRelation", classStudentRelation);
        return prefix + "/edit";
    }

    /**
     * 修改保存班级和学生关系
     */
    @RequiresPermissions("studentclassrelation:studentclassrelation:edit")
    @Log(title = "班级和学生关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ClassStudentRelation classStudentRelation) {
        return toAjax(classStudentRelationService.updateClassStudentRelation(classStudentRelation));
    }

    /**
     * 删除班级和学生关系
     */
    @RequiresPermissions("studentclassrelation:studentclassrelation:remove")
    @Log(title = "班级和学生关系", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(classStudentRelationService.deleteClassStudentRelationByTbStudentInfoIds(ids));
    }
}
