package com.barracuda.barracudateacher.student_info.controller;

import com.barracuda.barracudateacher.class_info.domain.ClassInfo;
import com.barracuda.barracudateacher.class_info.service.IClassInfoService;
import com.barracuda.barracudateacher.student_info.domain.StudentInfo;
import com.barracuda.barracudateacher.student_info.service.IStudentInfoService;
import com.barracuda.barracudateacher.tool.UserTool;
import com.barracuda.common.annotation.Log;
import com.barracuda.common.core.controller.BaseController;
import com.barracuda.common.core.domain.AjaxResult;
import com.barracuda.common.core.domain.Ztree;
import com.barracuda.common.core.page.TableDataInfo;
import com.barracuda.common.enums.BusinessType;
import com.barracuda.common.utils.poi.ExcelUtil;
import com.barracuda.web.controller.demo.domain.UserOperateModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生信息Controller
 *
 * @author barracuda
 * @date 2024-03-05
 */
@Controller
@RequestMapping("/studentinfo/studentinfo")
public class StudentInfoController extends BaseController {

    @Resource
    private IStudentInfoService studentInfoService;

    @Resource
    private IClassInfoService classInfoService;

    @RequiresPermissions("studentinfo:studentinfo:view")
    @GetMapping()
    public String studentinfo() {
        return "studentinfo/studentinfo/studentinfo";
    }

    /**
     * 查询学生信息列表
     */
    @RequiresPermissions("studentinfo:studentinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StudentInfo studentInfo, Long classId) {
        startPage();
        List<StudentInfo> list = studentInfoService.listStudentInfoOfClassId(studentInfo, classId);
        return getDataTable(list);
    }

    /**
     * 导出学生信息列表
     */
    @RequiresPermissions("studentinfo:studentinfo:export")
    @Log(title = "学生信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StudentInfo studentInfo, Long classId) {
        List<StudentInfo> list = studentInfoService.listStudentInfoOfClassId(studentInfo, classId);
        ExcelUtil<StudentInfo> util = new ExcelUtil<>(StudentInfo.class);
        return util.exportExcel(list, "学生信息数据");
    }

    /**
     * 新增学生信息
     */
    @GetMapping("/add")
    public String add(Long classId, ModelMap modelMap) {
        modelMap.put("classId", classId);
        return "studentinfo/studentinfo/add";
    }

    /**
     * 新增保存学生信息
     */
    @RequiresPermissions("studentinfo:studentinfo:add")
    @Log(title = "学生信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StudentInfo studentInfo, Long classId) {
        int i = studentInfoService.insertStudentInfo(studentInfo, classId);
        return toAjax(i);
    }

    /**
     * 修改学生信息
     */
    @RequiresPermissions("studentinfo:studentinfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        StudentInfo studentInfo = studentInfoService.selectStudentInfoById(id);
        mmap.put("studentInfo", studentInfo);
        return "studentinfo/studentinfo/edit";
    }

    /**
     * 修改保存学生信息
     */
    @RequiresPermissions("studentinfo:studentinfo:edit")
    @Log(title = "学生信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StudentInfo studentInfo) {
        return toAjax(studentInfoService.updateStudentInfo(studentInfo));
    }

    /**
     * 删除学生信息
     */
    @RequiresPermissions("studentinfo:studentinfo:remove")
    @Log(title = "学生信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(studentInfoService.deleteStudentInfoByIds(ids));
    }


    /**
     * 加载班级列表树
     */
    @RequiresPermissions("studentinfo:studentinfo:list")
    @GetMapping("/classTreeData")
    @ResponseBody
    public List<Ztree> classTreeData() {
        return classInfoService.selectClassTree(new ClassInfo());
    }

    /**
     * 下载模板
     */
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<StudentInfo> util = new ExcelUtil<>(StudentInfo.class);
        return util.importTemplateExcel("学生信息");
    }


    /**
     * 导入数据
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, Long classId) throws Exception {
        ExcelUtil<StudentInfo> util = new ExcelUtil<>(StudentInfo.class);
        List<StudentInfo> studentInfoList = util.importExcel(file.getInputStream());
        String message = studentInfoService.importStudent(studentInfoList, classId);
        return AjaxResult.success(message);
    }
}
