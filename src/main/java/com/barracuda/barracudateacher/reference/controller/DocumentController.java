package com.barracuda.barracudateacher.reference.controller;

import com.barracuda.barracudateacher.reference.domain.Document;
import com.barracuda.barracudateacher.reference.service.IDocumentService;
import com.barracuda.common.annotation.Log;
import com.barracuda.common.core.controller.BaseController;
import com.barracuda.common.core.domain.AjaxResult;
import com.barracuda.common.core.page.TableDataInfo;
import com.barracuda.common.enums.BusinessType;
import com.barracuda.common.exception.file.InvalidExtensionException;
import com.barracuda.common.utils.file.FileUploadUtils;
import com.barracuda.common.utils.file.MimeTypeUtils;
import com.barracuda.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 文档Controller
 *
 * @author barracuda
 * @date 2024-03-11
 */
@Controller
@RequestMapping("/document/document")
public class DocumentController extends BaseController {

    @Resource
    private IDocumentService documentService;

    @RequiresPermissions("document:document:view")
    @GetMapping()
    public String document() {
        return "document/document/document";
    }

    /**
     * 查询文档列表
     */
    @RequiresPermissions("document:document:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Document document) {
        startPage();
        List<Document> list = documentService.selectDocumentList(document);
        return getDataTable(list);
    }

    /**
     * 查询附件
     *
     * @param referenceId 参考资料ID
     */
    @PostMapping("/listByReferenceId")
    @ResponseBody
    public TableDataInfo listByReferenceId(Long referenceId) {
        List<Document> list = new ArrayList<>();
        if (referenceId != null) {
            list = documentService.listByReferenceId(referenceId);
        }
        return getDataTable(list);
    }


    /**
     * 导出文档列表
     */
    @RequiresPermissions("document:document:export")
    @Log(title = "文档", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Document document) {
        List<Document> list = documentService.selectDocumentList(document);
        ExcelUtil<Document> util = new ExcelUtil<>(Document.class);
        return util.exportExcel(list, "文档数据");
    }

    /**
     * 新增文档
     */
    @GetMapping("/add")
    public String add() {
        return "document/document/add";
    }

    /**
     * 新增保存文档
     */
    @RequiresPermissions("document:document:add")
    @Log(title = "文档", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Document document) {
        return toAjax(documentService.insertDocument(document));
    }

    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(List<MultipartFile> files) {
        List<Document> documentList = documentService.insertDocument(files);
        return AjaxResult.success(documentList);
    }

    /**
     * 修改文档
     */
    @RequiresPermissions("document:document:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Document document = documentService.selectDocumentById(id);
        mmap.put("document", document);
        return "document/document/edit";
    }

    /**
     * 修改保存文档
     */
    @RequiresPermissions("document:document:edit")
    @Log(title = "文档", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Document document) {
        return toAjax(documentService.updateDocument(document));
    }

    /**
     * 删除文档
     */
    @RequiresPermissions("document:document:remove")
    @Log(title = "文档", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(documentService.deleteDocumentByIds(ids));
    }

    @PostMapping("/checkAllowUpload")
    @ResponseBody
    public AjaxResult checkAllowUpload(List<MultipartFile> files) {
        if (files != null) {
            for (MultipartFile file : files) {
                try {
                    FileUploadUtils.assertAllowed(file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
                } catch (InvalidExtensionException e) {
                    return AjaxResult.error(e.getMessage());
                }
            }
        }
        return AjaxResult.success();
    }
}
