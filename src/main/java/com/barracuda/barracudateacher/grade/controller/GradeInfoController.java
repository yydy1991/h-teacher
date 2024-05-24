package com.barracuda.barracudateacher.grade.controller;

import com.barracuda.barracudateacher.grade.domain.GradeInfo;
import com.barracuda.barracudateacher.grade.service.IGradeInfoService;
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
 * 年级信息Controller
 *
 * @author barracuda
 * @date 2024-05-24
 */
@Controller
@RequestMapping("/grade/grade_info")
public class GradeInfoController extends BaseController {
    private String prefix = "grade/grade_info";

    @Resource
    private IGradeInfoService gradeInfoService;

    @RequiresPermissions("grade:grade_info:view")
    @GetMapping()
    public String gradeInfo() {
        return prefix + "/grade_info";
    }

    /**
     * 查询年级信息列表
     */
    @RequiresPermissions("grade:grade_info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(GradeInfo gradeInfo) {
        startPage();
        List<GradeInfo> list = gradeInfoService.selectGradeInfoList(gradeInfo);
        return getDataTable(list);
    }

    /**
     * 导出年级信息列表
     */
    @RequiresPermissions("grade:grade_info:export")
    @Log(title = "年级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(GradeInfo gradeInfo) {
        List<GradeInfo> list = gradeInfoService.selectGradeInfoList(gradeInfo);
        ExcelUtil<GradeInfo> util = new ExcelUtil<>(GradeInfo.class);
        return util.exportExcel(list, "年级信息数据");
    }

    /**
     * 新增年级信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存年级信息
     */
    @RequiresPermissions("grade:grade_info:add")
    @Log(title = "年级信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(GradeInfo gradeInfo) {
        return toAjax(gradeInfoService.insertGradeInfo(gradeInfo));
    }

    /**
     * 修改年级信息
     */
    @RequiresPermissions("grade:grade_info:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        GradeInfo gradeInfo = gradeInfoService.selectGradeInfoById(id);
        mmap.put("gradeInfo", gradeInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存年级信息
     */
    @RequiresPermissions("grade:grade_info:edit")
    @Log(title = "年级信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(GradeInfo gradeInfo) {
        return toAjax(gradeInfoService.updateGradeInfo(gradeInfo));
    }

    /**
     * 删除年级信息
     */
    @RequiresPermissions("grade:grade_info:remove")
    @Log(title = "年级信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(gradeInfoService.deleteGradeInfoByIds(ids));
    }
}
