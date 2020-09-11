package com.github.aiosign.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.aiosign.utils.StringUtils;
import lombok.Data;

import java.io.Serializable;

/**
 * 公共响应对象
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/5/11
 */
@Data
public abstract class AbstractSignResponse implements Serializable {
    /**
     * 网关成功状态码
     */
    public static final String SUCCESS_GATEWAY = "1000";
    /**
     * api成功的状态码
     */
    public static final String SUCCESS_API = "0";
    /**
     * 网关返回状态码 【必填】
     */
    protected String returnCode;
    /**
     * 网关返回信息 【必填】
     */
    protected String returnMessage;
    /**
     * 业务返回状态码 【选填】
     */
    protected String resultCode;
    /**
     * 业务返回信息 【选填】
     */
    protected String resultMessage;

    /**
     * 是否请求成功
     *
     * @return a boolean.
     */
    @JsonIgnore
    public boolean isSuccess() {
        boolean present = StringUtils.hasText(returnCode) && StringUtils.hasText(resultCode);
        boolean success = returnCode.equals(SUCCESS_GATEWAY) && resultCode.equals(SUCCESS_API);
        return present && success;
    }

    /**
     * 网关是否请求成功
     *
     * @return a boolean.
     */
    public boolean isGatewaySuccess() {
        return StringUtils.hasText(returnCode) && returnCode.equals(SUCCESS_GATEWAY);
    }

    /**
     * 请求api是否成功
     *
     * @return a boolean.
     */
    public boolean isApiSuccess() {
        return StringUtils.hasText(resultCode) && returnCode.equals(SUCCESS_GATEWAY);
    }
}
