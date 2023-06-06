package com.github.aiosign.csh;

import com.github.aiosign.AbstractSignTest;
import com.github.aiosign.enums.ContentType;
import com.github.aiosign.enums.HttpMethod;
import com.github.aiosign.module.request.CommonRequest;
import com.github.aiosign.module.request.TemplateLockRequest;
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
 *
 * @author Zhu Dunfeng
 * @date 2023/5/30
 */
@Slf4j
public class TemplateCshTest extends AbstractSignTest {
    /**
     * 添加模板
     */
    @Test
    public void add() {
        HashMap<String, Object> templateAddRequestBody = new HashMap<>();
        // 文件id
        templateAddRequestBody.put("file_id","135630e7111720a0924e003102d8efe2");
        // 模板名称
        templateAddRequestBody.put("name","测试模板");

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/template/add"); // 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(templateAddRequestBody);// 请求体

        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 查询模板
     */
    @Test
    public void query() {
        HashMap<String, Object> templateQueryRequestBody = new HashMap<>();
        // 模板id
        templateQueryRequestBody.put("template_id","e939a7bbb7a9dc26d4e14f1f4c28d20b");
        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/template/query"); // 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(templateQueryRequestBody);// 请求体

        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 锁定模板
     */
    @Test
    public void lock() {
        TemplateLockRequest templateLockRequest = new TemplateLockRequest();
        HashMap<String, Object> templateLockRequestBody = new HashMap<>();
        // 模板id
        templateLockRequestBody.put("template_id","e939a7bbb7a9dc26d4e14f1f4c28d20b");
        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/template/lock"); // 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(templateLockRequestBody);// 请求体

        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 解锁模板
     */
    @Test
    public void unlock() {
        HashMap<String, Object> templateUnlockRequestBody = new HashMap<>();
        // 模板id
        templateUnlockRequestBody.put("template_id","e939a7bbb7a9dc26d4e14f1f4c28d20b");

        CommonRequest request = new CommonRequest();
        request.setApiUri("/v1/template/unlock"); // 请求Api地址
        request.setNeedToken(true);// 是否需要token
        request.setContentType(ContentType.JSON);// 请求头类型
        request.setMethod(HttpMethod.POST);// 请求方法
        request.setRequestBody(templateUnlockRequestBody);// 请求体

        CommonResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
}
