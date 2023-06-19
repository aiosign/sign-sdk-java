package com.github.aiosign.csh;

import com.alibaba.fastjson.JSON;
import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.response.CommonResponse;
import com.github.aiosign.utils.AESUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * 城商行定制
 * <p>
 * 均使用原始调用Api，除上传以及下载文件，实体类方式后期不再维护
 * requestBody请求参数:可序列化对象,具体请求参数，请参考Api文档中调用接口请求参数
 * reponse响应参数:Object,具体响应参数，请参考Api文档中调用接口响应参数
 *
 * @author Zhu Dunfeng
 * @date 2023/5/29
 */
@Slf4j
public class SealCshTest extends AbstractSignTest {

    /**
     * 获取用户所有印章
     */
    @Test
    public void queryUserSealInfos() {
        HashMap<String, String> requestBody = new HashMap<>(2);
        requestBody.put("user_id", "00740939959804514304");

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/seal/getSealInfos"); // 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体

        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", JSON.toJSONString(execute.getData()));
    }

    /**
     * 电子印章申领
     */
    @Test
    public void applySeal() {
        // AESKey为密钥，对接时下发
        final String AESKey = "U6rmwyUKmPU7zCML";
        HashMap<String, String> requestBody = new HashMap<>(2);
        String areaCode="450100";
        //企业名称（密）
        requestBody.put("user_name", AESUtils.encrypt(AESKey,"测试山东国盾网"));
        //企业社会信用代码（密）
        requestBody.put("credit_code", AESUtils.encrypt(AESKey, "235122212233212"));
        //地区编码
        requestBody.put("area_code",areaCode);

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/seal/apply-seal-en"); // 请求Api地址
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
