package com.github.aiosign.csh;

import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.request.ContractAddRequest;
import com.github.aiosign.module.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;

/**
 * 城商行定制
 * <p>
 * 均使用原始调用Api，除上传以及下载文件，实体类方式后期不再维护
 * requestBody请求参数:可序列化对象,具体请求参数，请参考Api文档中调用接口请求参数
 * reponse响应参数:Object,具体响应参数，请参考Api文档中调用接口响应参数
 * @author Zhu Dunfeng
 * @date 2023/5/29
 */
@Slf4j
public class ContractCshTest extends AbstractSignTest {

    /**
     * 添加合同
     */
    @Test
    public void add() {
        HashMap<String, Object> requestBody = new HashMap<>();
        // 合同名字
        requestBody.put("name","测试");
        // 文件id
        requestBody.put("file_id","16988a470f3744fc923ce25016e8b522");
        // 用户id不能为空
        requestBody.put("user_id","10144942136450173440");
        // 描述信息
        requestBody.put("description", "测试描述");

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/contract/add"); // 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(requestBody);// 请求体

        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }


}
