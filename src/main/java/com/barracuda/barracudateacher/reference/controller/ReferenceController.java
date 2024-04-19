package com.barracuda.barracudateacher.reference.controller;

import com.barracuda.barracudateacher.reference.domain.Document;
import com.barracuda.barracudateacher.reference.domain.Reference;
import com.barracuda.barracudateacher.reference.domain.ReferenceDocumentRelation;
import com.barracuda.barracudateacher.reference.service.IDocumentService;
import com.barracuda.barracudateacher.reference.service.IReferenceDocumentRelationService;
import com.barracuda.barracudateacher.reference.service.IReferenceService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 参考资料Controller
 *
 * @author barracuda
 */
@Controller
@RequestMapping("/reference/reference")
public class ReferenceController extends BaseController {

    @Resource
    private IReferenceService referenceService;

    @Resource
    private IDocumentService documentService;

    @Resource
    private IReferenceDocumentRelationService referenceDocumentRelationService;

    @RequiresPermissions("reference:reference:view")
    @GetMapping()
    public String reference() {
        return "reference/reference/reference";
    }

    /**
     * 查询参考资料列表
     */
    @RequiresPermissions("reference:reference:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Reference reference) {
        startPage();
        List<Reference> list = referenceService.selectReferenceList(reference);
        return getDataTable(list);
    }

    /**
     * 导出参考资料列表
     */
    @RequiresPermissions("reference:reference:export")
    @Log(title = "参考资料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Reference reference) {
        List<Reference> list = referenceService.selectReferenceList(reference);
        ExcelUtil<Reference> util = new ExcelUtil<>(Reference.class);
        return util.exportExcel(list, "参考资料数据");
    }

    /**
     * 新增参考资料
     */
    @GetMapping("/add")
    public String add() {
        return "reference/reference/add";
    }

    /**
     * 新增保存参考资料
     */
    @RequiresPermissions("reference:reference:add")
    @Log(title = "参考资料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Reference reference, List<MultipartFile> files) {
        referenceService.insertReference(reference);
        List<Document> documentList = documentService.insertDocument(files);
        List<Long> documentIdList = documentList.stream().map(Document::getId).collect(Collectors.toList());
        referenceDocumentRelationService.insertReferenceDocumentRelation(reference.getId(), documentIdList);
        return AjaxResult.success();
    }

    /**
     * 修改参考资料
     */
    @RequiresPermissions("reference:reference:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Reference reference = referenceService.selectReferenceById(id);
        mmap.put("reference", reference);
        return "reference/reference/edit";
    }

    /**
     * 修改保存参考资料
     */
    @RequiresPermissions("reference:reference:edit")
    @Log(title = "参考资料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Reference reference) {
        return toAjax(referenceService.updateReference(reference));
    }

    /**
     * 删除参考资料
     */
    @RequiresPermissions("reference:reference:remove")
    @Log(title = "参考资料", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(referenceService.deleteReferenceByIds(ids));
    }
}