package com.github.aiosign.csh;

import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.response.CommonResponse;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author Zhu Dunfeng
 * @date 2023/6/20 10:38
 */
public class TokenCshTest extends AbstractSignTest {

    /**
     * 获取token
     */
    @Test
    public void getToken() {
        HashMap<String, Object> requestBody = new HashMap<>();
        requestBody.put("app_id","137544453355293184");
        requestBody.put("app_secret","AnRiWjTaJMeoIspUsi");
        requestBody.put("grant_type","client_credentials");
        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/oauth/token");// 请求Api地址
        request.setNeedToken(false);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体
        CommonResponse response = signClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("获取token成功，响应值为" + response.getData());
        }
    }

}
