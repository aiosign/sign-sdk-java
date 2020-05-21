package com.sdgdw.sign.module.request;

import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.FileItem;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.FileUploadResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


/**
 * @author yangyouwang
 * @description 上传文件请求
 * @since 2020/5/19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadRequest extends AbstractSignRequest<FileUploadResponse> {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件类型不能为空
     */
    private String fileType;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 文件参数
     */
    private FileItem fileItem;

    @Override
    public RequestInfo<FileUploadResponse> getRequestInfo() {
        RequestInfo<FileUploadResponse> requestInfo=new RequestInfo<>();
        requestInfo.setContentType(ContentType.MULTIPART);
        requestInfo.setApiUri("file/upload");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(FileUploadResponse.class);
        Map<String, FileItem> map = new HashMap<>(2);
        map.put("file",fileItem);
        requestInfo.setFileParams(map);
        Map<String, String> params = requestInfo.getParams();
        params.put("file_name",fileName);
        params.put("file_type",fileType);
        params.put("user_id",userId);
        return requestInfo;
    }
}
