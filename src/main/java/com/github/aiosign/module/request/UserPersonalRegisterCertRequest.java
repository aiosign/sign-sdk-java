package com.github.aiosign.module.request;

import com.github.aiosign.base.AbstractComposeRequest;
import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.client.SignClient;
import com.github.aiosign.module.response.UserCertPrepareResponse;
import com.github.aiosign.module.response.UserPersonalRegisterCertResponse;
import com.github.aiosign.module.response.UserPersonalRegisterResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 个人用户注册 与 用户的证书申请
 *
 * @author modificial
 * @since 2020/4/1
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPersonalRegisterCertRequest extends AbstractComposeRequest<UserPersonalRegisterCertResponse> {


    private UserPersonalRegisterRequest userPersonalRegisterRequest;


    @Override
    public UserPersonalRegisterCertResponse execute(SignClient signClient) {
        UserPersonalRegisterCertResponse registerCertResponse = new UserPersonalRegisterCertResponse();

        // 用户注册
        UserPersonalRegisterResponse userPersonalRegisterResponse = signClient.execute(userPersonalRegisterRequest);
        if (!userPersonalRegisterResponse.isSuccess()) {
            setReturnCode(registerCertResponse, userPersonalRegisterResponse);
            return registerCertResponse;
        }

        // 用户证书申请
        UserIdentityRequest userIdentityRequest = new UserIdentityRequest();
        userIdentityRequest.setUserId(userPersonalRegisterResponse.getData().getUserId());
        UserCertPrepareResponse userCertPrepareResponse = signClient.execute(userIdentityRequest);
        if (!userCertPrepareResponse.isSuccess()) {
            setReturnCode(registerCertResponse, userCertPrepareResponse);
            return registerCertResponse;
        }

        registerCertResponse.setUserId(userPersonalRegisterResponse.getData().getUserId());
        registerCertResponse.setData(userCertPrepareResponse.getData());
        setReturnCode(registerCertResponse, userCertPrepareResponse);
        return registerCertResponse;
    }


    private void setReturnCode(UserPersonalRegisterCertResponse registerCertResponse, AbstractSignResponse signResponse) {
        registerCertResponse.setReturnCode(signResponse.getReturnCode());
        registerCertResponse.setResultMessage(signResponse.getResultMessage());
        registerCertResponse.setResultCode(signResponse.getResultCode());
        registerCertResponse.setReturnMessage(signResponse.getReturnMessage());
    }


}
