package com.github.aiosign;

import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.SealBatchResponse;
import com.github.aiosign.module.response.SealQueryResponse;
import com.github.aiosign.module.response.SealResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author yangyouwang
 * @description 印章Test
 * @since 2020/5/13
 */
@Slf4j
public class SealTest extends AbstractSignTest {

    /**
     * 添加印章
     */
    @Test
    public void add() {
        SealAddRequest sealAddRequest = new SealAddRequest();
        // 用户id
        sealAddRequest.setUserId("00716661208384163840");
        // 印章名字
        sealAddRequest.setSealName("测试印章");
        // 印章类型
        sealAddRequest.setSealType("01");
        // 印章文件id
        sealAddRequest.setFileId("1f63ed0d5fd6631b788115abbb6e3bec");
        // 印章规格
        sealAddRequest.setSize("40*40");
        // 描述
        sealAddRequest.setDescription("测试");
        SealResponse execute = signClient.execute(sealAddRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 删除印章
     */
    @Test
    public void remove() {
        SealRemoveRequest sealRemoveRequest = new SealRemoveRequest();
        // 印章id
        sealRemoveRequest.setSealId("a9e48474650448709a3b577ce4f72234");
        SealBatchResponse execute = signClient.execute(sealRemoveRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 锁定印章
     */
    @Test
    public void lock() {
        SealLockRequest sealLockRequest = new SealLockRequest(); // 印章id
        sealLockRequest.setSealId("a9e48474650448709a3b577ce4f72234");
        SealBatchResponse execute = signClient.execute(sealLockRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 解锁印章
     */
    @Test
    public void unlock() {
        SealUnLockRequest sealUnLockRequest = new SealUnLockRequest(); // 印章id
        sealUnLockRequest.setSealId("a9e48474650448709a3b577ce4f72234");
        SealBatchResponse execute = signClient.execute(sealUnLockRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 查询印章
     */
    @Test
    public void query() {
        SealIdentityRequest sealIdentityRequest = new SealIdentityRequest(); // 印章id
        sealIdentityRequest.setSealId("a9e48474650448709a3b577ce4f72234");
        SealQueryResponse execute = signClient.execute(sealIdentityRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
}
