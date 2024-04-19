package com.barracuda.barracudateacher.reference.controller;

import com.barracuda.barracudateacher.reference.domain.ReferenceDocumentRelation;
import com.barracuda.barracudateacher.reference.service.IReferenceDocumentRelationService;
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
 * 参考资料和文档关系Controller
 *
 * @author barracuda
 */
@Controller
@RequestMapping("/reference/referencedocumentrelation")
public class ReferenceDocumentRelationController extends BaseController {
    private String prefix = "reference/referencedocumentrelation";

    @Resource
    private IReferenceDocumentRelationService referenceDocumentRelationService;

    @RequiresPermissions("reference:referencedocumentrelation:view")
    @GetMapping()
    public String referencedocumentrelation() {
        return prefix + "/referencedocumentrelation";
    }

    /**
     * 查询参考资料和文档关系列表
     */
    @RequiresPermissions("reference:referencedocumentrelation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ReferenceDocumentRelation referenceDocumentRelation) {
        startPage();
        List<ReferenceDocumentRelation> list = referenceDocumentRelationService.selectReferenceDocumentRelationList(referenceDocumentRelation);
        return getDataTable(list);
    }

    /**
     * 导出参考资料和文档关系列表
     */
    @RequiresPermissions("reference:referencedocumentrelation:export")
    @Log(title = "参考资料和文档关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReferenceDocumentRelation referenceDocumentRelation) {
        List<ReferenceDocumentRelation> list = referenceDocumentRelationService.selectReferenceDocumentRelationList(referenceDocumentRelation);
        ExcelUtil<ReferenceDocumentRelation> util = new ExcelUtil<>(ReferenceDocumentRelation.class);
        return util.exportExcel(list, "参考资料和文档关系数据");
    }

    /**
     * 新增参考资料和文档关系
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存参考资料和文档关系
     */
    @RequiresPermissions("reference:referencedocumentrelation:add")
    @Log(title = "参考资料和文档关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ReferenceDocumentRelation referenceDocumentRelation) {
        return toAjax(referenceDocumentRelationService.insertReferenceDocumentRelation(referenceDocumentRelation));
    }

    /**
     * 修改参考资料和文档关系
     */
    @RequiresPermissions("reference:referencedocumentrelation:edit")
    @GetMapping("/edit/{refReferenceId}")
    public String edit(@PathVariable("refReferenceId") Long refReferenceId, ModelMap mmap) {
        ReferenceDocumentRelation referenceDocumentRelation = referenceDocumentRelationService.selectReferenceDocumentRelationByRefReferenceId(refReferenceId);
        mmap.put("referenceDocumentRelation", referenceDocumentRelation);
        return prefix + "/edit";
    }

    /**
     * 修改保存参考资料和文档关系
     */
    @RequiresPermissions("reference:referencedocumentrelation:edit")
    @Log(title = "参考资料和文档关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ReferenceDocumentRelation referenceDocumentRelation) {
        return toAjax(referenceDocumentRelationService.updateReferenceDocumentRelation(referenceDocumentRelation));
    }

    /**
     * 删除参考资料和文档关系
     */
    @RequiresPermissions("reference:referencedocumentrelation:remove")
    @Log(title = "参考资料和文档关系", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(referenceDocumentRelationService.deleteReferenceDocumentRelationByRefReferenceIds(ids));
    }
}
