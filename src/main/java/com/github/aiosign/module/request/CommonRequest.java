package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.FileItem;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.CommonResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 提供原始调用签章Api 请求参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonRequest extends AbstractSignRequest<CommonResponse> {
    /**
     * 请求方法
     */
    private HttpMethod method = HttpMethod.POST;
    /**
     * 是否需要token
     */
    private boolean needToken = true;
    /**
     * 请求头类型
     */
    private ContentType contentType=ContentType.JSON;
    /**
     * 请求体
     */
    private Serializable requestBody;
    /**
     * 请求uri
     */
    private String apiUri;
    /**
     * query参数
     */
    private Map<String, String> params = new HashMap<>(16);
    /**
     * 文件上传的参数
     */
    private Map<String, FileItem> fileParams = new HashMap<>(4);

    @Override
    @JsonIgnore
    public RequestInfo<CommonResponse> getRequestInfo() {
        RequestInfo<CommonResponse> requestInfo=new RequestInfo<>();
        requestInfo.setApiUri(apiUri);
        requestInfo.setContentType(contentType);
        requestInfo.setNeedToken(needToken);
        requestInfo.setMethod(method);
        requestInfo.setRequestBody(requestBody);
        requestInfo.setParams(params);
        requestInfo.setFileParams(fileParams);
        requestInfo.setResponseType(CommonResponse.class);
        return requestInfo;
    }
}
