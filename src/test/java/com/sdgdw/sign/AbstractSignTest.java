package com.sdgdw.sign;

import com.sdgdw.sign.client.SignClient;
import com.sdgdw.sign.client.support.DefaultSignClient;
import org.junit.Before;

/**
 * 基础的测试类，抽取公共方法
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
        signClient = new DefaultSignClient("http://localhost:8081/v1/","710511183694286848","UfMTWTuvONXkCzmqtm");
    }


}
