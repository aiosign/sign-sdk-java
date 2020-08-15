package com.github.aiosign.module.request;

import com.github.aiosign.base.AbstractComposeRequest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.client.SignClient;
import com.github.aiosign.module.response.ContractAddAndFileResponse;
import com.github.aiosign.module.response.ContractAddResponse;
import com.github.aiosign.module.response.FileUploadResponse;
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
public class ContractAddAndFileRequest extends AbstractComposeRequest<ContractAddAndFileResponse> {


    private final String fileType = "contract";

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
     * 合同名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;


    @Override
    public ContractAddAndFileResponse execute(SignClient signClient) {
        ContractAddAndFileResponse contractAddAndFileResponse = new ContractAddAndFileResponse();

        FileUploadRequest fileUploadRequest = new FileUploadRequest();
        fileUploadRequest.setFileType(fileType);
        fileUploadRequest.setFileName(fileName);
        fileUploadRequest.setUserId(userId);
        fileUploadRequest.setFileItem(fileItem);
        //   文件上传
        FileUploadResponse fileUploadResponse = signClient.execute(fileUploadRequest);
        if (!fileUploadResponse.isSuccess()) {
            setReturnCode(contractAddAndFileResponse, fileUploadResponse);
            return contractAddAndFileResponse;
        }

        // 印章添加
        ContractAddRequest contractAddRequest = new ContractAddRequest();
        contractAddRequest.setName(name);
        contractAddRequest.setFileId(fileUploadResponse.getData().getFileId());
        contractAddRequest.setUserId(userId);
        contractAddRequest.setDescription(description);
        ContractAddResponse execute = signClient.execute(contractAddRequest);
        if (!execute.isSuccess()) {
            setReturnCode(contractAddAndFileResponse, execute);
            return contractAddAndFileResponse;
        }
        contractAddAndFileResponse.setFileId(fileUploadResponse.getData().getFileId());
        contractAddAndFileResponse.setContractId(execute.getData().getContractId());
        setReturnCode(contractAddAndFileResponse, execute);
        return contractAddAndFileResponse;
    }


}
