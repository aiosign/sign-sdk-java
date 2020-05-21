package com.sdgdw.sign.client;

import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.AbstractSignResponse;

import java.io.IOException;

/**
 * 签章所用的客户端
 * @author modificial
 * @description
 * @since 2020/5/11
 */
public interface SignClient {
    /**
     *
     * @param signRequest
     * @param <T>
     * @return
     */
    <T extends AbstractSignResponse> T execute(AbstractSignRequest<T> signRequest);
}
