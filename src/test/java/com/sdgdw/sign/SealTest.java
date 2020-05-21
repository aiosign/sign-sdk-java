package com.sdgdw.sign;

import com.sdgdw.sign.module.request.*;
import com.sdgdw.sign.module.response.SealBatchResponse;
import com.sdgdw.sign.module.response.SealQueryResponse;
import com.sdgdw.sign.module.response.SealResponse;
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
        sealAddRequest.setUserId("00710519812799483904");
        // 印章名字
        sealAddRequest.setSealName("测试");
        // 印章类型
        sealAddRequest.setSealType("99");
        // 印章文件id
        sealAddRequest.setFileId("2ba1b307524a5eece0edb26cfc5fb64b");
        // 印章规格
        sealAddRequest.setSize("40*40");
        // 描述
        sealAddRequest.setDescription("测试");
        SealResponse execute = signClient.execute(sealAddRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 删除印章
     */
    @Test
    public void remove() {
        SealRemoveRequest sealRemoveRequest = new SealRemoveRequest();
        // 印章id
        sealRemoveRequest.setSealId("de9af3e9a1dd866d26deb5fda69c8405");
        SealBatchResponse execute = signClient.execute(sealRemoveRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 锁定印章
     */
    @Test
    public void lock() {
        SealLockRequest sealLockRequest = new SealLockRequest(); // 印章id
        sealLockRequest.setSealId("de9af3e9a1dd866d26deb5fda69c8405");
        SealBatchResponse execute = signClient.execute(sealLockRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 解锁印章
     */
    @Test
    public void unlock() {
        SealUnLockRequest sealUnLockRequest = new SealUnLockRequest(); // 印章id
        sealUnLockRequest.setSealId("de9af3e9a1dd866d26deb5fda69c8405");
        SealBatchResponse execute = signClient.execute(sealUnLockRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 查询印章
     */
    @Test
    public void query() {
        SealIdentityRequest sealIdentityRequest = new SealIdentityRequest(); // 印章id
        sealIdentityRequest.setSealId("de9af3e9a1dd866d26deb5fda69c8405");
        SealQueryResponse execute = signClient.execute(sealIdentityRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }
}
