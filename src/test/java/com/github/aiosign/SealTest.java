package com.github.aiosign;

import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
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
        SealResponse.SealModule data = execute.getData();
        //
        data.getSealId();


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

    /**
     * 获取用户所有印章
     */
    @Test
    public void queryUserSealInfos() {
        SealInfosRequest sealInfosRequest = new SealInfosRequest();
        sealInfosRequest.setUserId("00765245060136194048");//用户ID
        SealInfosResponse execute = signClient.execute(sealInfosRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 根据用户、印章类型获取印章信息
     */
    @Test
    public void querySealInfosByUserOrType() {
        SealInfosByUserOrTypeRequest request = new SealInfosByUserOrTypeRequest();
        request.setUserIds("00765245060136194048,00745413246592897024");//用户ID，以逗号分隔
        request.setSealTypes("02,99,05");//印章类型，以逗号分隔
        request.setPageNum(1);//数据页码
        request.setPageSize(10);//数据长度
        SealInfosByUserOrTypeResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
}
