package com.github.aiosign.csh;

import com.alibaba.fastjson.JSON;
import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * 城商行定制
 *
 * 代码仅作参考示例
 * <p>
 * 均使用原始调用Api，除上传以及下载文件，实体类方式后期不再维护
 * requestBody请求参数:可序列化对象,具体请求参数，请参考Api文档中调用接口请求参数
 * reponse响应参数:Object,具体响应参数，请参考Api文档中调用接口响应参数
 * @date 2023/6/2 10:17
 */
@Slf4j
public class SignLogCshTest extends AbstractSignTest {

    @Test
    public void signLogQuery(){
        HashMap<String, String> requestBody = new HashMap<>(3);
        requestBody.put("sign_id", "a3067f9662bb4f16bcfac84d43992903");

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/sign/log/query"); // 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体

        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
    }
}
