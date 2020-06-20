package com.github.aiosign.module.request;

import com.github.aiosign.base.AbstractComposeRequest;
import com.github.aiosign.client.SignClient;
import com.github.aiosign.module.response.UserCertPrepareResponse;
import com.github.aiosign.module.response.UserCompanyRegisterCertResponse;
import com.github.aiosign.module.response.UserCompanyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author 侯存路
 * @description 企业用户注册 与 用户证书申请
 * @since 2020/6/16 9:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCompanyRegisterCertRequest extends AbstractComposeRequest<UserCompanyRegisterCertResponse> {


    /**
     * 企业用户注册
     */
    private UserCompanyRegisterRequest userCompanyRegisterRequest;


    @Override
    public UserCompanyRegisterCertResponse execute(SignClient signClient) {
        UserCompanyRegisterCertResponse registerCertResponse = new UserCompanyRegisterCertResponse();

        // 企业用户注册
        UserCompanyResponse userCompanyResponse = signClient.execute(userCompanyRegisterRequest);
        if (!userCompanyResponse.isSuccess()) {
            setReturnCode(registerCertResponse, userCompanyResponse);
            return registerCertResponse;
        }

        // 用户证书申请
        UserIdentityRequest userIdentityRequest = new UserIdentityRequest();
        userIdentityRequest.setUserId(userCompanyResponse.getData().getUserId());
        UserCertPrepareResponse userCertPrepareResponse = signClient.execute(userIdentityRequest);
        if (!userCertPrepareResponse.isSuccess()) {
            setReturnCode(registerCertResponse, userCertPrepareResponse);
            return registerCertResponse;
        }

        registerCertResponse.setUserId(userCompanyResponse.getData().getUserId());
        registerCertResponse.setData(userCertPrepareResponse.getData());
        setReturnCode(registerCertResponse, userCertPrepareResponse);
        return registerCertResponse;
    }




}
