package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SignCheckResponse;
import com.github.aiosign.module.response.SignCheckV2Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangyouwang
 * @since 2020/5/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileCheckV2Request extends AbstractSignRequest<SignCheckV2Response> {

    /**
     * 文件参数
     */
    private FileItem fileItem;


    @Override
    @JsonIgnore
    public RequestInfo<SignCheckV2Response> getRequestInfo() {
        RequestInfo<SignCheckV2Response> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.MULTIPART);
        requestInfo.setApiUri("/v2/sign/check/file");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SignCheckV2Response.class);
        Map<String, FileItem> map = new HashMap<>(2);
        map.put("file", fileItem);
        requestInfo.setFileParams(map);
        return requestInfo;
    }
}
