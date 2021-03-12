package com.github.aiosign.module.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.RequestInfo;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.response.DirectSignV2Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * v2 一步签署
 * @author Administrator
 * @version 1.0
 * @date 2021/3/3 9:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectSignV2Request extends AbstractSignRequest<DirectSignV2Response> {

    /**
     * 合同id
     */
    private String contractId;

    /**
     * 个人用户信息
     */
    private List<DirectSignV2Request.PersonalInfo> personalInfo;

    /**
     * 企业用户信息
     */
    private List<DirectSignV2Request.CompanyInfo> companyInfo;

    @Data
    public static class PersonalInfo implements Serializable {
        /**
         * 用户名
         */
        private String name;
        /**
         * 身份证号码
         */
        private String idNumber;
        /**
         * 手机号
         */
        private String phone;
        /**
         * 地区编码
         */
        private String areaCode;
        /**
         * 签署页码
         */
        private Integer pageNum;
        /**
         * 印章文件内容base64
         */
        private String sealFileContent;
        /**
         * 签章需要的x坐标
         */
        private Integer horizontal;
        /**
         * 签章需要的y坐标
         */
        private Integer vertical;
        /**
         * 印章宽度
         */
        private Integer sealWidth;
        /**
         * 印章高度
         */
        private Integer sealHeight;
    }

    @Data
    public static class CompanyInfo implements Serializable {
        /**
         * 用户ID
         */
        private String userId;
        /**
         * 印章ID
         */
        private String sealId;
        /**
         * 签署页码
         */
        private Integer pageNum;
        /**
         * 签章需要的x坐标
         */
        private Integer horizontal;
        /**
         * 签章需要的y坐标
         */
        private Integer vertical;
        /**
         * 印章宽度
         */
        private Integer sealWidth;
        /**
         * 印章高度
         */
        private Integer sealHeight;
    }

    @Override
    @JsonIgnore
    public RequestInfo<DirectSignV2Response> getRequestInfo() {
        RequestInfo<DirectSignV2Response> requestInfo = new RequestInfo<>();
        requestInfo.setContentType(ContentType.JSON);
        requestInfo.setApiUri("/v2/sign/directSign");
        requestInfo.setMethod(HttpMethod.POST);
        requestInfo.setNeedToken(true);
        requestInfo.setResponseType(DirectSignV2Response.class);
        requestInfo.setRequestBody(this);
        return requestInfo;
    }
}
