package com.github.aiosign.module.request;

import com.github.aiosign.base.AbstractComposeRequest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.client.SignClient;
import com.github.aiosign.module.response.FileUploadResponse;
import com.github.aiosign.module.response.TemplateAddAndFileResponse;
import com.github.aiosign.module.response.TemplateAddResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @since 2020/6/16 9:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateAddAndFileRequest extends AbstractComposeRequest<TemplateAddAndFileResponse> {


    private final String fileType = "template";

    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件
     */
    private FileItem fileItem;
    /**
     * 合同名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;


    @Override

    public TemplateAddAndFileResponse execute(SignClient signClient) {
        TemplateAddAndFileResponse templateAddAndFileResponse = new TemplateAddAndFileResponse();

        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setFileType(fileType);
        fileUploadRequest.setFileName(fileName);
        fileUploadRequest.setFileItem(fileItem);
        //   文件上传
        FileUploadResponse fileUploadResponse = signClient.execute(fileUploadRequest);
        if (!fileUploadResponse.isSuccess()) {
            setReturnCode(templateAddAndFileResponse, fileUploadResponse);
            return templateAddAndFileResponse;
        }

        // 模板添加
        TemplateAddRequest templateAddRequest = new TemplateAddRequest();
        // 文件id
        templateAddRequest.setFileId(fileUploadResponse.getData().getFileId());
        // 模板名称
        templateAddRequest.setName(name);
        TemplateAddResponse execute = signClient.execute(templateAddRequest);

        if (!execute.isSuccess()) {
            setReturnCode(templateAddAndFileResponse, execute);
            return templateAddAndFileResponse;
        }

        templateAddAndFileResponse.setFileId(fileUploadResponse.getData().getFileId());
        templateAddAndFileResponse.setTemplateId(execute.getData().getTemplateId());

        setReturnCode(templateAddAndFileResponse, execute);
        return templateAddAndFileResponse;
    }


}
