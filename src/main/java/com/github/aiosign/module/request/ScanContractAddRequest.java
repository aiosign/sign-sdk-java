package com.github.aiosign.module.request;

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
public class ScanContractAddRequest extends AbstractSignRequest<ScanContractAddResponse> {
    /**
     * 合同id
     */
    private String contractId;
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
    private String qrCodeWidth;
    /**
     * 二维码高度 单位px
     * Min 50
     * Max 900
     */
    private String qrCodeHeight;


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
    }


    @Override
    public RequestInfo<ScanContractAddResponse> getRequestInfo() {
        RequestInfo<ScanContractAddResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v1/scan/contract/add");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(ScanContractAddResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
