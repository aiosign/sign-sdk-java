package com.sdgdw.sign.client;

import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.AbstractSignResponse;

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
     * @param signRequest a {@link com.sdgdw.sign.base.AbstractSignRequest} object.
     * @return a T object.
     */
    <T extends AbstractSignResponse> T execute(AbstractSignRequest<T> signRequest);
}
