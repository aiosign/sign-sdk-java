package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.MeetingSignResponse;
import com.github.aiosign.module.response.SignResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 会签传入参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingSignRequest extends AbstractSignRequest<SignResponse> {
    /**
     * 自定义签署ID
     */
    private String signId;
    /**
     * 是否完结合同，1完结，0未完结，默认是0
     */
    private Integer isContractFinish = 0;

    /**
     * 是否渲染页面，0否，1是，默认是0
     */
    private Integer isRender = 0;
    /**
     * 合同id
     */
    private String contractId;

    /**
     * 签章详细信息
     */
    private List<SignDetail> signDetails;

    /**
     * 签章详细参数
     */
    @Data
    public static class SignDetail implements Serializable {
        /**
         * 用户id
         */
        private String userId;

        /**
         * 印章id
         */
        private String sealId;

        /**
         * 签章模式，1单个电子签章，3骑缝章,默认是1，单个电子签章
         */
        private Integer layout = 1;

        /**
         * 页码
         */
        private Integer pageNum;

        /**
         * 印章宽度
         */
        private Integer signWidth;

        /**
         * 高度
         */
        private Integer signHeight;

        /**
         * 签署距离合同上方距离
         */
        private Integer signTop;

        /**
         * 签署距离合同左方距离
         */
        private Integer signLeft;

        /**
         * 旋转角度，精确1位小数
         */
        private Double rotate = 0.0D;

    }

    @Override
    @JsonIgnore
    public RequestInfo<SignResponse> getRequestInfo() {
        RequestInfo<SignResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/sign/meeting/single");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(SignResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
