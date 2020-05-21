package com.sdgdw.sign.client.support;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdgdw.sign.base.AbstractSignRequest;
import com.sdgdw.sign.base.AbstractSignResponse;
import com.sdgdw.sign.base.FileItem;
import com.sdgdw.sign.base.RequestInfo;
import com.sdgdw.sign.client.SignClient;
import com.sdgdw.sign.enums.ContentType;
import com.sdgdw.sign.exception.SignException;
import com.sdgdw.sign.utils.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * 默认的签章客户端实现
 *
 * @author modificial
 * @description
 * @since 2020/5/11
 */
@Data
@AllArgsConstructor
public class DefaultSignClient implements SignClient {
    /**
     * 默认连接超时时间
     */
    public static final Integer CONNECT_TIME_OUT = 3000;
    /**
     * 默认读取超时时间
     */
    public static final Integer READ_TIME_OUT = 3000;
    /**
     * 网关根地址 【必填】
     */
    private String rootUri;
    /**
     * 连接超时时间 【选填】
     */
    private Integer connectTimeOut;
    /**
     * 读取数据超时时间 【选填】
     */
    private Integer readTimeOut;
    /**
     * 代理的主机地址 【选填】
     */
    private String proxyHost;
    /**
     * 代理的端口号 【选填】
     */
    private int proxyPort;
    /**
     * 应用id 【必填】
     */
    private String appId;
    /**
     * 应用秘钥
     */
    private String appSecret;
    /**
     * 是否需要校验返回结果
     */
    private boolean checkResult;

    /**
     * 构造器
     *
     * @param rootUri   网关地址前缀
     * @param proxyHost 代理主机
     * @param proxyPort 代理端口
     * @param appId     应用id
     * @param appSecret 应用秘钥
     */
    public DefaultSignClient(String rootUri, String proxyHost, Integer proxyPort, String appId, String appSecret,boolean checkResult) {
        this(rootUri, CONNECT_TIME_OUT, READ_TIME_OUT, proxyHost, proxyPort, appId, appSecret,checkResult);
    }

    /**
     * 不使用代理的构造器
     *
     * @param rootUri   网关地址前缀
     * @param appId     应用id
     * @param appSecret 应用秘钥
     */
    public DefaultSignClient(String rootUri, String appId, String appSecret) {
        this(rootUri, null, 0, appId, appSecret,false);
    }

    /**
     * 执行http请求，并返回数据
     *
     * @param signRequest
     * @return
     */
    @Override
    public <T extends AbstractSignResponse> T execute(AbstractSignRequest<T> signRequest) {
        Assert.notNull(signRequest, "请求对象不能为空");
        RequestInfo<T> requestInfo = Optional.ofNullable(signRequest.getRequestInfo()).orElseThrow(() -> new IllegalArgumentException("参数不能为空"));
        //对参数进行检验
        requestInfo.check();
        try {
            //发起请求，解析结果
            T t = requestForResult(requestInfo);
            //如果需要对结果进行校验，校验后返回
            if(checkResult){
                checkResult(t);
            }
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new SignException("5001", "调用api发生错误");
    }

    /**
     * 发起请求并获取结果
     *
     * @param requestInfo
     * @return
     * @throws IOException
     */
    private <T extends AbstractSignResponse> T requestForResult(RequestInfo<T> requestInfo) throws IOException {
        String result = null;
        String apiUrl = rootUri + requestInfo.getApiUri();
        //如果需要传递token
        if(requestInfo.isNeedToken()){
            String token = TokenManager.getToken(this);
            apiUrl=apiUrl.concat("?access_token=").concat(token);
        }
        switch (requestInfo.getMethod()) {
            case POST:
                if (requestInfo.getContentType().equals(ContentType.JSON)) {
                    ObjectMapper objectMapper = ObjectMapperHolder.INSTANCE.getInstance();
                    Serializable requestBody = requestInfo.getRequestBody();
                    String body = objectMapper.writeValueAsString(requestBody);
                    String sign = SignUtils.createSign(body,appSecret);
                    result = WebUtils.doPostJson(apiUrl, body, this.connectTimeOut, this.readTimeOut, proxyHost, proxyPort, sign);
                } else if (requestInfo.getContentType().equals(ContentType.FORM_URLENCODED)) {
                    Map<String, String> params = requestInfo.getParams();
                    result = WebUtils.doPost(apiUrl, params, "UTF-8", connectTimeOut, readTimeOut, proxyHost, proxyPort);
                } else if (requestInfo.getContentType().equals(ContentType.MULTIPART)) {
                    Map<String, String> params = requestInfo.getParams();
                    Map<String, FileItem> fileParams = requestInfo.getFileParams();
                    result = WebUtils.doPost(apiUrl, params, fileParams, "UTF-8", connectTimeOut, readTimeOut, proxyHost, proxyPort);
                }
                break;
            case GET:
                Map<String, String> params = requestInfo.getParams();
                result = WebUtils.doGet(apiUrl, params);
                break;
            default:
                break;
        }
        Assert.hasText(result, "调用api失败");
        ObjectMapper objectMapper = ObjectMapperHolder.INSTANCE.getInstance();
        try {
            objectMapper.readTree(result);
        } catch (IOException e) {
            throw new SignException("5001", "返回的数据不是json");
        }
        return objectMapper.readValue(result, requestInfo.getResponseType());
    }

    /**
     * 对返回的结果进行检测
     *
     * @param response
     */
    public void checkResult(AbstractSignResponse response) {
        boolean success = response.isSuccess();
        if (success) {
            return;
        } else if (response.isGatewaySuccess()) {
            throw new SignException(response.getResultCode(), response.getResultMessage());
        } else {
            throw new SignException(response.getReturnCode(), response.getReturnMessage());
        }
    }
}
