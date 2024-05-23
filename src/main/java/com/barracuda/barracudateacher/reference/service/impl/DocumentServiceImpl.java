package com.barracuda.barracudateacher.reference.service.impl;

import com.barracuda.barracudateacher.reference.domain.Document;
import com.barracuda.barracudateacher.reference.mapper.DocumentMapper;
import com.barracuda.barracudateacher.reference.service.IDocumentService;
import com.barracuda.common.config.BarracudaConfig;
import com.barracuda.common.config.ServerConfig;
import com.barracuda.common.core.text.Convert;
import com.barracuda.common.utils.DateUtils;
import com.barracuda.common.utils.file.FileUploadUtils;
import com.barracuda.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文档Service业务层处理
 *
 * @author barracuda
 */
@Slf4j
@Service
public class DocumentServiceImpl implements IDocumentService {
    @Resource
    private DocumentMapper documentMapper;
    @Resource
    private ServerConfig serverConfig;

    /**
     * 查询文档
     *
     * @param id 文档主键
     * @return 文档
     */
    @Override
    public Document selectDocumentById(Long id) {
        return documentMapper.selectDocumentById(id);
    }

    /**
     * 查询文档列表
     *
     * @param document 文档
     * @return 文档
     */
    @Override
    public List<Document> selectDocumentList(Document document) {
        return documentMapper.selectDocumentList(document);
    }

    /**
     * 新增文档
     *
     * @param document 文档
     * @return 结果
     */
    @Override
    public int insertDocument(Document document) {
        document.setCreateTime(DateUtils.getNowDate());
        return documentMapper.insertDocument(document);
    }

    /**
     * 修改文档
     *
     * @param document 文档
     * @return 结果
     */
    @Override
    public int updateDocument(Document document) {
        document.setUpdateTime(DateUtils.getNowDate());
        return documentMapper.updateDocument(document);
    }

    /**
     * 批量删除文档
     *
     * @param ids 需要删除的文档主键
     * @return 结果
     */
    @Override
    public int deleteDocumentByIds(String ids) {
        return documentMapper.deleteDocumentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文档信息
     *
     * @param id 文档主键
     * @return 结果
     */
    @Override
    public int deleteDocumentById(Long id) {
        return documentMapper.deleteDocumentById(id);
    }

    /**
     * 新增文件
     *
     * @param files 文件
     * @return 文档
     */
    @Override
    public List<Document> insertDocument(List<MultipartFile> files) {
        List<Document> result = new ArrayList<>();
        try {
            if (files != null) {
                // 上传文件路径
                String filePath = BarracudaConfig.getUploadPath();
                for (MultipartFile file : files) {
                    // 上传并返回新文件名称
                    String filePathAndName = FileUploadUtils.upload(filePath, file);
                    long size = file.getSize();
                    String originalFilename = file.getOriginalFilename();
                    String contentType = file.getContentType();
                    log.debug("filePathAndName:{},originalFilename:{},contentType:{},size:{}"
                            , filePathAndName, originalFilename, contentType, size);

                    Document document = new Document();
                    document.setDocumentType(contentType);
                    document.setDocumentSize(size);
                    document.setDocumentAddress(filePathAndName);
                    document.setDocumentName(originalFilename);
                    insertDocument(document);
                    result.add(document);
                }
            }
        } catch (Exception e) {
            log.error("insertDocument error." + e.getMessage(), e);
        }
        return result;
    }


    /**
     * 查询附件
     *
     * @param referenceId 参考资料ID
     */
    @Override
    public List<Document> listByReferenceId(Long referenceId) {
        Assert.notNull(referenceId, "Reference id is null.");
        return documentMapper.listByReferenceId(referenceId);
    }
}
