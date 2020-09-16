package com.github.aiosign;

import com.github.aiosign.bean.TextParam;
import com.github.aiosign.client.SignClient;
import com.github.aiosign.client.support.DefaultSignClient;
import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangyouwang
 * @description 模板Test
 * @since 2020/5/13
 */
@Slf4j
public class TemplateTest extends AbstractSignTest {

    /**
     * 查询模板
     */
    @Test
    public void query() {
        TemplateQueryRequest templateQueryRequest = new TemplateQueryRequest();
        // 模板id
        templateQueryRequest.setTemplateId("e939a7bbb7a9dc26d4e14f1f4c28d20b");
        TemplateQueryResponse execute = signClient.execute(templateQueryRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 删除模板
     */
    @Test
    public void delete() {
        TemplateDeleteRequest templateDeleteRequest = new TemplateDeleteRequest();
        // 模板id
        templateDeleteRequest.setTemplateId("e939a7bbb7a9dc26d4e14f1f4c28d20b");
        TemplateDeleteResponse execute = signClient.execute(templateDeleteRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 添加模板
     */
    @Test
    public void add() {
        TemplateAddRequest templateAddRequest = new TemplateAddRequest();
        // 文件id
        templateAddRequest.setFileId("135630e7111720a0924e003102d8efe2");
        // 模板名称
        templateAddRequest.setName("测试模板");
        TemplateAddResponse execute = signClient.execute(templateAddRequest);
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
        // 模板id
        templateLockRequest.setTemplateId("e939a7bbb7a9dc26d4e14f1f4c28d20b");
        TemplateLockResponse execute = signClient.execute(templateLockRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 解锁模板
     */
    @Test
    public void unlock() {
        TemplateUnlockRequest templateUnlockRequest = new TemplateUnlockRequest();
        // 模板id
        templateUnlockRequest.setTemplateId("e939a7bbb7a9dc26d4e14f1f4c28d20b");
        TemplateUnlockResponse execute = signClient.execute(templateUnlockRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
    /**
     * 解锁模板
     */
    @Test
    public void fill() {
      SignClient signClient = new DefaultSignClient("http://192.168.17.23:32286", "728283996928757760",
                "GgorGhRTdTSWSubpPV");
        TemplateFillRequest templateFillRequest = new TemplateFillRequest();
        // 模板id
        templateFillRequest.setTemplateId("e939a7bbb7a9dc26d4e14f1f4c28d20b");
        templateFillRequest.setUserId("00727604780889427968");
        templateFillRequest.setName("测试合同");
        List<TextParam> textParams=new ArrayList<>();
        textParams.add(new TextParam("year","2020"));
        templateFillRequest.setSimpleFormFields(textParams);
        TemplateFillResponse execute = signClient.execute(templateFillRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

}
