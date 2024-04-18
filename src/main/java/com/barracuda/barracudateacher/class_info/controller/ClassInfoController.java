package com.barracuda.barracudateacher.class_info.controller;

import com.barracuda.barracudateacher.class_info.domain.ClassInfo;
import com.barracuda.barracudateacher.class_info.service.IClassInfoService;
import com.barracuda.common.annotation.Log;
import com.barracuda.common.core.controller.BaseController;
import com.barracuda.common.core.domain.AjaxResult;
import com.barracuda.common.core.page.TableDataInfo;
import com.barracuda.common.enums.BusinessType;
import com.barracuda.common.utils.ShiroUtils;
import com.barracuda.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 班级信息Controller
 *
 * @author barracuda
 */
@Controller
@RequestMapping("/classinfo/classinfo")
public class ClassInfoController extends BaseController {
    private String prefix = "classinfo/classinfo";

    @Resource
    private IClassInfoService classInfoService;

    @RequiresPermissions("classinfo:classinfo:view")
    @GetMapping()
    public String classinfo() {
        return prefix + "/classinfo";
    }

    /**
     * 查询班级信息列表
     */
    @RequiresPermissions("classinfo:classinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ClassInfo classInfo) {
        startPage();
        Long userId = ShiroUtils.getUserId();
        List<ClassInfo> list = classInfoService.listClassInfo(classInfo, userId);
        return getDataTable(list);
    }

    /**
     * 导出班级信息列表
     */
    @RequiresPermissions("classinfo:classinfo:export")
    @Log(title = "班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ClassInfo classInfo) {
        List<ClassInfo> list = classInfoService.selectClassInfoList(classInfo);
        ExcelUtil<ClassInfo> util = new ExcelUtil<>(ClassInfo.class);
        return util.exportExcel(list, "班级信息数据");
    }

    /**
     * 新增班级信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存班级信息
     */
    @RequiresPermissions("classinfo:classinfo:add")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ClassInfo classInfo) {
        return toAjax(classInfoService.addSave(classInfo));
    }

    /**
     * 修改班级信息
     */
    @RequiresPermissions("classinfo:classinfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ClassInfo classInfo = classInfoService.selectClassInfoById(id);
        mmap.put("classInfo", classInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存班级信息
     */
    @RequiresPermissions("classinfo:classinfo:edit")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ClassInfo classInfo) {
        return toAjax(classInfoService.updateClassInfo(classInfo));
    }

    /**
     * 删除班级信息
     */
    @RequiresPermissions("classinfo:classinfo:remove")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(classInfoService.deleteClassInfoByIds(ids));
    }
}
