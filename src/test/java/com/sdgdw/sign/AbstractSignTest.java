package com.sdgdw.sign;

import com.sdgdw.sign.client.SignClient;
import com.sdgdw.sign.client.support.DefaultSignClient;
import com.sdgdw.sign.module.request.TokenRequest;
import com.sdgdw.sign.module.response.TokenResponse;
import org.junit.Before;
import org.junit.Test;

/**
 * 基础的测试类，实例化请求客户端
 *
 * @author modificial
 * @description
 * @since 2020/5/19
 */
public abstract class AbstractSignTest {

    protected SignClient signClient;

    /**
     * 初始化客户端
     */
    @Before
    public void init() {
        signClient = new DefaultSignClient("api网关地址", "您的appid", "您的appsecret");
    }

    /**
     * 获取签名值
     */
    @Test
    public void getToken() {
        TokenRequest tokenRequest = new TokenRequest("appid", "appsecret");
        TokenResponse response = signClient.execute(tokenRequest);
        if (response.isSuccess()) {
            System.out.println("获取token成功，响应值为" + response.getData());
        }
    }
}
