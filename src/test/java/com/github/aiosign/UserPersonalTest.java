package com.github.aiosign;


import com.github.aiosign.module.request.*;
import com.github.aiosign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author yangyouwang
 * @description 个人用户Test
 * @since 2020/5/13
 */
@Slf4j
public class UserPersonalTest extends AbstractSignTest {
    /**
     * 个人用户注册
     */
    @Test
    public void register() {
        UserPersonalRegisterRequest userPersonalLockRequest = new UserPersonalRegisterRequest();
        // 姓名
        userPersonalLockRequest.setUserName("测试001");
        // 地区编码
        userPersonalLockRequest.setAreaCode("370101");
        // 手机号
        userPersonalLockRequest.setPhone("15684532141");
        // 证件类型
        userPersonalLockRequest.setIdType("111");
        // 证件号
        userPersonalLockRequest.setIdNumber("371525199309870986");
        // 邮箱地址
        userPersonalLockRequest.setMail("1562310354@qq.com");
        // 描述
        userPersonalLockRequest.setDescription("测试001");
        UserPersonalRegisterResponse execute = signClient.execute(userPersonalLockRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 获取个人用户ID以及注册
     */
    @Test
    public void registerOrQuery() {
        UserPersonalRegisterOrQueryRequest request = new UserPersonalRegisterOrQueryRequest();
        // 姓名
        request.setUserName("测试001");
        // 地区编码
        request.setAreaCode("370101");
        // 手机号
        request.setPhone("15684532141");
        // 证件类型
        request.setIdType("111");
        // 证件号
        request.setIdNumber("371525199309870986");
        // 邮箱地址
        request.setMail("1562310354@qq.com");
        //申请证书方式，0不申请（默认）;1同步申请；2异步申请
        request.setCertApplyType(1);
        UserPersonalRegisterOrQueryResponse execute = signClient.execute(request);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 用户锁定
     */
    @Test
    public void lock() {
        UserPersonalLockRequest userPersonalLockRequest = new UserPersonalLockRequest();
        // 用户id
        userPersonalLockRequest.setUserId("00716661208384163840");
        UserPersonalLockResponse execute = signClient.execute(userPersonalLockRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 用户解锁
     */
    @Test
    public void unLock() {
        UserPersonalUnLockRequest userPersonalUnLockRequest = new UserPersonalUnLockRequest();
        // 用户id
        userPersonalUnLockRequest.setUserId("00716661208384163840");
        UserPersonalUnLockResponse execute = signClient.execute(userPersonalUnLockRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 用户详情
     */
    @Test
    public void userinfo() {
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        // 用户id
        userInfoRequest.setUserId("00716661208384163840");
        UserInfoResponse execute = signClient.execute(userInfoRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 注销用户
     */
    @Test
    public void removeUser() {
        UserPersonalRemoveRequest userPersonalRemoveRequest = new UserPersonalRemoveRequest("00716661208384163840");
        UserPersonalRemoveResponse execute = signClient.execute(userPersonalRemoveRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
}
