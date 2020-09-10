package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SealResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 印章添加参数
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SealAddRequest extends AbstractSignRequest<SealResponse> {


    /**
     * 用户id
     */
    private String userId;

    /**
     * 印章名字
     */
    private String sealName;

    /**
     * 印章类型
     */
    private String sealType;

    /**
     * 印章文件id
     */
    private String fileId;

    /**
     * 印章规格
     */
    private String size;

    /**
     * 描述信息
     */
    private String description;

    @Override
    @JsonIgnore
    public RequestInfo<SealResponse> getRequestInfo() {
        RequestInfo<SealResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("seal/add");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SealResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
