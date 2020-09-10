package com.github.aiosign.base;


import com.github.aiosign.client.SignClient;

import java.io.Serializable;

/**
 * 相关业务接口组合
 *
 * @author 侯存路
 * @since 2020/6/15 18:08
 */
public abstract class AbstractComposeRequest<T extends AbstractSignResponse> implements Serializable {


    /**
     * 执行相关业务
     */
    public abstract <T extends AbstractSignResponse> T execute(SignClient signClient);


    public void setReturnCode(AbstractSignResponse abstractSignResponse, AbstractSignResponse signResponse) {
        abstractSignResponse.setReturnCode(signResponse.getReturnCode());
        abstractSignResponse.setResultMessage(signResponse.getResultMessage());
        abstractSignResponse.setResultCode(signResponse.getResultCode());
        abstractSignResponse.setReturnMessage(signResponse.getReturnMessage());
    }


}
