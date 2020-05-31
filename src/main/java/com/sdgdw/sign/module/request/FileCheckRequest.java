package com.sdgdw.sign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.FileItem;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.enums.HttpMethod;
import com.sdgdw.sign.module.response.SignCheckResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>FileCheckRequest class.</p>
 *
 * @author yangyouwang
 * @version $Id: $Id
 * @description 文件验签请求
 * @since 2020/5/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileCheckRequest extends AbstractSignRequest<SignCheckResponse> {

    /**
     * 文件参数
     */
    private FileItem fileItem;


    /**
     * {@inheritDoc}
     */
    @Override
    @JsonIgnore
    public RequestInfo<SignCheckResponse> getRequestInfo() {
        RequestInfo<SignCheckResponse> requestInfo=new RequestInfo<>();
        requestInfo.setContentType(ContentType.MULTIPART);
        requestInfo.setApiUri("sign/check/file");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SignCheckResponse.class);
        Map<String, FileItem> map = new HashMap<>(2);
        map.put("file",fileItem);
        requestInfo.setFileParams(map);
        return requestInfo;
    }
}
