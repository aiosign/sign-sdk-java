package com.github.aiosign.module.request;

import com.github.aiosign.base.AbstractComposeRequest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.client.SignClient;
import com.github.aiosign.module.response.FileUploadResponse;
import com.github.aiosign.module.response.SealAddAndFileResponse;
import com.github.aiosign.module.response.SealResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @description
 * @since 2020/6/16 9:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SealAddAndFileRequest extends AbstractComposeRequest<SealAddAndFileResponse> {


    private final String fileType = "impression";

    /**
     * 用户标识
     */
    private String userId;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件
     */
    private FileItem fileItem;
    /**
     * 印章名称
     */
    private String sealName;
    /**
     * 印章类型
     */
    private String sealType;
    /**
     * 印章规格
     */
    private String size;
    /**
     * 描述
     */
    private String description;


    @Override

    public SealAddAndFileResponse execute(SignClient signClient) {
        SealAddAndFileResponse registerCertResponse = new SealAddAndFileResponse();

        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setFileType(fileType);
        fileUploadRequest.setFileName(fileName);
        fileUploadRequest.setUserId(userId);
        fileUploadRequest.setFileItem(fileItem);
        //   文件上传
        FileUploadResponse fileUploadResponse = signClient.execute(fileUploadRequest);
        if (!fileUploadResponse.isSuccess()) {
            setReturnCode(registerCertResponse, fileUploadResponse);
            return registerCertResponse;
        }

        // 印章添加
        SealAddRequest sealAddRequest = new SealAddRequest();
        sealAddRequest.setUserId(userId);
        sealAddRequest.setSealName(sealName);
        sealAddRequest.setSealType(sealType);
        sealAddRequest.setFileId(fileUploadResponse.getData().getFileId());
        sealAddRequest.setSize(size);
        sealAddRequest.setDescription(description);
        SealResponse execute = signClient.execute(sealAddRequest);
        if (!execute.isSuccess()) {
            setReturnCode(registerCertResponse, execute);
            return registerCertResponse;
        }
        registerCertResponse.setFileId(fileUploadResponse.getData().getFileId());
        registerCertResponse.setSealId(execute.getData().getSealId());
        return registerCertResponse;
    }


}
