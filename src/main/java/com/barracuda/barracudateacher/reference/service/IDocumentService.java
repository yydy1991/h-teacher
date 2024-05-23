package com.barracuda.barracudateacher.reference.service;

import java.util.List;

import com.barracuda.barracudateacher.reference.domain.Document;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文档Service接口
 *
 * @author barracuda
 * @date 2024-03-11
 */
public interface IDocumentService {
    /**
     * 查询文档
     *
     * @param id 文档主键
     * @return 文档
     */
    public Document selectDocumentById(Long id);

    /**
     * 查询文档列表
     *
     * @param document 文档
     * @return 文档集合
     */
    public List<Document> selectDocumentList(Document document);

    /**
     * 新增文档
     *
     * @param document 文档
     * @return 结果
     */
    public int insertDocument(Document document);

    /**
     * 修改文档
     *
     * @param document 文档
     * @return 结果
     */
    public int updateDocument(Document document);

    /**
     * 批量删除文档
     *
     * @param ids 需要删除的文档主键集合
     * @return 结果
     */
    public int deleteDocumentByIds(String ids);

    /**
     * 删除文档信息
     *
     * @param id 文档主键
     * @return 结果
     */
    public int deleteDocumentById(Long id);

    /**
     * 新增文件
     *
     * @return 文档
     */
    List<Document> insertDocument(List<MultipartFile> files);

    /**
     * 查询附件
     *
     * @param referenceId 参考资料ID
     */
    List<Document> listByReferenceId(Long referenceId);
}
