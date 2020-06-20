package com.github.aiosign.client;

import com.github.aiosign.base.AbstractComposeRequest;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.AbstractSignResponse;

/**
 * 签章所用的客户端
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/5/11
 */
public interface SignClient {
    /**
     * 执行http请求，并返回结果
     *
     * @param signRequest a {@link AbstractSignRequest} object.
     * @param <T>         返回值类型
     * @return a T object.
     */
    <T extends AbstractSignResponse> T execute(AbstractSignRequest<T> signRequest);


    /**
     * 相关业务接口组合
     *
     * @param composeRequest
     * @param <T>
     * @return
     */
    <T extends AbstractSignResponse> T execute(AbstractComposeRequest<T> composeRequest);

}
