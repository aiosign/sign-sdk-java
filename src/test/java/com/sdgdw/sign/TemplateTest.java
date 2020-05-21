package com.sdgdw.sign;

import com.sdgdw.sign.client.SignClient;
import com.sdgdw.sign.client.support.DefaultSignClient;
import com.sdgdw.sign.module.request.*;
import com.sdgdw.sign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

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
        TemplateQueryRequest templateQueryRequest =new TemplateQueryRequest();
        // 模板id
        templateQueryRequest.setTemplateId("699307492530343124");
        TemplateQueryResponse execute = signClient.execute(templateQueryRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 删除模板
     */
    @Test
    public void delete() {
        TemplateDeleteRequest templateDeleteRequest =new TemplateDeleteRequest();
        // 模板id
        templateDeleteRequest.setTemplateId("699307492530343124");
        TemplateDeleteResponse execute = signClient.execute(templateDeleteRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 添加模板
     */
    @Test
    public void add() {
        TemplateAddRequest templateAddRequest =new TemplateAddRequest();
        // 文件id
        templateAddRequest.setFileId("0002d1510e15f5bd6139782700ce49e2");
        // 模板名称
        templateAddRequest.setName("测试");
        TemplateAddResponse execute = signClient.execute(templateAddRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 锁定模板
     */
    @Test
    public void lock() {
        TemplateLockRequest templateLockRequest =new TemplateLockRequest();
        // 模板id
        templateLockRequest.setTemplateId("699307492530343124");
        TemplateLockResponse execute = signClient.execute(templateLockRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 解锁模板
     */
    @Test
    public void unlock() {
        TemplateUnlockRequest templateUnlockRequest =new TemplateUnlockRequest();
        // 模板id
        templateUnlockRequest.setTemplateId("699307492530343124");
        TemplateUnlockResponse execute = signClient.execute(templateUnlockRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }
}
