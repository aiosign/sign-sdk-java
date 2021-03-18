package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.ScanContractAddResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author WeiShuai
 * @Date 2020/9/18 0018 下午 02:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventCertScanContractAddRequest extends AbstractSignRequest<ScanContractAddResponse> {
    /**
     * 合同id
     */
    private String contractId;
    /**
     * 是否完结合同，1完结，0未完结，默认是0
     */
    private Integer isContractFinish = 0;

    /**
     * 是否渲染页面，0否，1是，默认是0
     */
    private Integer isRender = 0;
    /**
     * 用户id
     */
    private String userId;

    /**
     * QR拼接URL
     */
    private String url;
    /**
     * 二维码失效时间
     */
    private String expireTime;
    /**
     * 二维码宽度 单位 px
     * Min 50
     * Max 900
     */
    private Integer qrCodeWidth;
    /**
     * 二维码高度 单位px
     * Min 50
     * Max 900
     */
    private Integer qrCodeHeight;


    /**
     * 签章备注
     */
    private String remark;

    /**
     * 签章信息集合
     */
    private List<SignParams> fields;

    /**
     * 签章参数
     */
    @Data
    public static class SignParams implements Serializable {
        /**
         * 印章高度，精确1位小数
         */
        private Double height;

        /**
         * 水平横坐标
         */
        private Double horizontal;
        /**
         * 第几页
         */
        private Integer pageNumber;

        /**
         * 印章id
         */
        private String sealId;

        /**
         * 垂直纵坐标
         */
        private Double vertical;
        /**
         * 印章宽度，精确1位小数
         */
        private Double width;
        /**
         * 印章高度，精确1位小数
         */
        private Double rotate = 0.0D;

        /**
         * 签章模式，1单个电子签章，3骑缝章,默认是1，单个电子签章
         */
        private Integer layout = 1;
    }


    @Override
    @JsonIgnore
    public RequestInfo<ScanContractAddResponse> getRequestInfo() {
        RequestInfo<ScanContractAddResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/scan/event_cert_contract/add");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ScanContractAddResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
