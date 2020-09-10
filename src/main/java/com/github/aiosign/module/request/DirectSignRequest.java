package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.DirectSignResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * 一步签署 请求参数
 *
 * @author modificial
 * @since 2020/4/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectSignRequest extends AbstractSignRequest<DirectSignResponse> {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户类型,1个人，2企业
     */
    private String userType;

    /**
     * 证件号（个人对应身份证号，企业对应社会统一信用代码）
     */
    private String idNumber;

    /**
     * 合同文件
     */
    private String contractFileContent;


    private List<SignDetail> signFields;

    /**
     * 签署详情
     */
    @Data
    public static class SignDetail implements Serializable {
        /**
         * 印章文件
         */
        private String sealFileContent;
        /**
         * 印章宽度
         */
        private Double sealWidth;
        /**
         * 印章高度
         */
        private Double sealHeight;
        /**
         * 签章页数
         */
        private Integer pageNum;
        /**
         * 签章需要的x坐标
         */
        private Double horizontal;
        /**
         * 签章需要的y坐标
         */
        private Double vertical;
    }

    @Override
    @JsonIgnore
    public RequestInfo<DirectSignResponse> getRequestInfo() {
        RequestInfo<DirectSignResponse> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("event_cert_sign/directSign");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(DirectSignResponse.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
