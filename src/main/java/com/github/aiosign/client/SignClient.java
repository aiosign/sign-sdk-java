package com.github.aiosign.client;

import com.github.aiosign.base.AbstractComposeRequest;
import com.github.aiosign.base.AbstractSignRequest;
import com.github.aiosign.base.AbstractSignResponse;

import java.io.OutputStream;

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
     * 组合接口
     *
     * @param composeRequest
     * @param <T>
     * @return
     */
    <T extends AbstractSignResponse> T execute(AbstractComposeRequest<T> composeRequest);

    /**
     * 下载文件
     *
     * @param baseUri      接口uri地址
     * @param fileId       文件id
     * @param outputStream 输出流
     */
    void download(String baseUri, String fileId, OutputStream outputStream);

    /**
     * 组装下载地址
     */
    String downloadFileURL(String fileId);

    /**
     * 获取token
     *
     * @return
     */
    String getToken();


}
