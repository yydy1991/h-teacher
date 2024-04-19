package com.barracuda.barracudateacher.class_info.controller;

import com.barracuda.barracudateacher.class_info.domain.UserClassRelation;
import com.barracuda.barracudateacher.class_info.service.IUserClassRelationService;
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
 * 教师和班级关系Controller
 *
 * @author barracuda
 * @date 2024-03-04
 */
@Controller
@RequestMapping("/userclass/userclass")
public class UserClassRelationController extends BaseController {
    private String prefix = "userclass/userclass";

    @Resource
    private IUserClassRelationService userClassRelationService;

    @RequiresPermissions("userclass:userclass:view")
    @GetMapping()
    public String userclass() {
        return prefix + "/userclass";
    }

    /**
     * 查询教师和班级关系列表
     */
    @RequiresPermissions("userclass:userclass:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserClassRelation userClassRelation) {
        startPage();
        List<UserClassRelation> list = userClassRelationService.selectUserClassRelationList(userClassRelation);
        return getDataTable(list);
    }

    /**
     * 导出教师和班级关系列表
     */
    @RequiresPermissions("userclass:userclass:export")
    @Log(title = "教师和班级关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserClassRelation userClassRelation) {
        List<UserClassRelation> list = userClassRelationService.selectUserClassRelationList(userClassRelation);
        ExcelUtil<UserClassRelation> util = new ExcelUtil<>(UserClassRelation.class);
        return util.exportExcel(list, "教师和班级关系数据");
    }

    /**
     * 新增教师和班级关系
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存教师和班级关系
     */
    @RequiresPermissions("userclass:userclass:add")
    @Log(title = "教师和班级关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserClassRelation userClassRelation) {
        return toAjax(userClassRelationService.insertUserClassRelation(userClassRelation));
    }

    /**
     * 修改教师和班级关系
     */
    @RequiresPermissions("userclass:userclass:edit")
    @GetMapping("/edit/{sysUserId}")
    public String edit(@PathVariable("sysUserId") Long sysUserId, ModelMap mmap) {
        UserClassRelation userClassRelation = userClassRelationService.selectUserClassRelationBySysUserId(sysUserId);
        mmap.put("userClassRelation", userClassRelation);
        return prefix + "/edit";
    }

    /**
     * 修改保存教师和班级关系
     */
    @RequiresPermissions("userclass:userclass:edit")
    @Log(title = "教师和班级关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserClassRelation userClassRelation) {
        return toAjax(userClassRelationService.updateUserClassRelation(userClassRelation));
    }

    /**
     * 删除教师和班级关系
     */
    @RequiresPermissions("userclass:userclass:remove")
    @Log(title = "教师和班级关系", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(userClassRelationService.deleteUserClassRelationBySysUserIds(ids));
    }
}
