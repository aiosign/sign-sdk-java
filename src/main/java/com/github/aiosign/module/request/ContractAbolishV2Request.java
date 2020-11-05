package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ContractAbolishResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 合同废除V2接口
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractAbolishV2Request extends AbstractSignRequest<ContractAbolishResponse> {
    /**
     * 签署记录ID
     */
    private String signId;
    /**
     * 用户id
     */
    private String userId;

    private List<SignParams> fields;

    @Data
    public static class SignParams implements Serializable {

        /**
         * 页码
         */
        private Integer pageNumber;
        /**
         * 水平横坐标
         */
        private Double horizontal;
        /**
         * 垂直纵坐标
         */
        private Double vertical;
        /**
         * 印章宽度
         */
        private Double width;
        /**
         * 印章高度
         */
        private Double height;
    }

    @Override
    @JsonIgnore
    public RequestInfo<ContractAbolishResponse> getRequestInfo() {
        RequestInfo<ContractAbolishResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v2/contract/abolish");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ContractAbolishResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
