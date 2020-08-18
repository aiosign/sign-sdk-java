package com.github.aiosign.base;

import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.utils.Assert;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求必要信息
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/5/11
 */
@Data
public class RequestInfo<T extends AbstractSignResponse> implements Serializable {
    /**
     * 请求方法
     */
    private HttpMethod method = HttpMethod.POST;
    /**
     * 是否需要token
     */
    private boolean needToken = true;
    /**
     * 请求头类型
     */
    private ContentType contentType;
    /**
     * 返回的类型
     */
    private Class<T> responseType;
    /**
     * 请求体
     */
    private Serializable requestBody;
    /**
     * 请求uri
     */
    private String apiUri;
    /**
     * query参数
     */
    private Map<String, String> params = new HashMap<>(16);
    /**
     * 文件上传的参数
     */
    private Map<String, FileItem> fileParams = new HashMap<>(4);

    /**
     * 对参数进行检验
     */
    public void check() {
        Assert.notNull(method, "请求方法不能为空");
        Assert.notNull(contentType, "请求头类型不能为空");
        Assert.notNull(responseType, "返回值类型不能为空");
        Assert.hasText(apiUri, "请求地址不能为空");
        if (contentType.equals(ContentType.JSON)) {
            Assert.notNull(requestBody, "请求体不能为空");
        } else if (contentType.equals(ContentType.FORM_URLENCODED)) {
            Assert.notEmpty(params, "query参数不能为空");
        } else if (contentType.equals(ContentType.MULTIPART)) {
            Assert.notEmpty(fileParams,"文件上传的参数不能为空");
        }
    }
}
