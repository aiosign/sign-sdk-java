package com.sdgdw.sign.base;

import java.io.Serializable;

/**
 * 抽象的签章请求对象
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/5/11
 */
public abstract class AbstractSignRequest<T extends AbstractSignResponse> implements Serializable {
    /**
     * 返回请求的必要参数信息
     *
     * @return a {@link com.sdgdw.sign.base.RequestInfo} object.
     */
    public abstract RequestInfo<T> getRequestInfo();
}
