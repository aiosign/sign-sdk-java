package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.SealInfosByUserOrTypeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 根据用户、印章类型所有印章 请求参数
 *
 * @author modificial
 * @since 2020/4/11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SealInfosByUserOrTypeRequest extends AbstractSignRequest<SealInfosByUserOrTypeResponse> {
    /**
     * 用户ID，以逗号分隔
     */
    private String userIds;
    /**
     * 印章类型，以逗号分隔
     */
    private String sealTypes;
    /**
     * 数据页码
     */
    private Integer pageNum;
    /**
     * 数据长度
     */
    private Integer pageSize;

    @Override
    @JsonIgnore
    public RequestInfo<SealInfosByUserOrTypeResponse> getRequestInfo() {
        RequestInfo<SealInfosByUserOrTypeResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/seal/getSealInfosByUsersOrSealTypes");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SealInfosByUserOrTypeResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
