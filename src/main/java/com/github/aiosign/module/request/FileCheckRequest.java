package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SignCheckResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>FileCheckRequest class.</p>
 * 文件验签请求
 *
 * @author yangyouwang
 * @version $Id: $Id
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
        RequestInfo<SignCheckResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.MULTIPART);
        requestInfo.setApiUri("sign/check/file");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SignCheckResponse.class);
        Map<String, FileItem> map = new HashMap<>(2);
        map.put("file", fileItem);
        requestInfo.setFileParams(map);
        return requestInfo;
    }
}
