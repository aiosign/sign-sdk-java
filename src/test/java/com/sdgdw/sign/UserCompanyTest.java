package com.sdgdw.sign;

import com.sdgdw.sign.module.request.*;
import com.sdgdw.sign.module.response.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author yangyouwang
 * @description 企业用户Test
 * @since 2020/5/13
 */
@Slf4j
public class UserCompanyTest extends AbstractSignTest {

    /**
     * 企业用户注册
     */
    @Test
    public void register() {
        UserCompanyRegisterRequest userCompanyRegisterRequest = new UserCompanyRegisterRequest();
        // 企业名称
        userCompanyRegisterRequest.setUserName("测试");
        // 地区编码
        userCompanyRegisterRequest.setAreaCode("370101");
        // 单位类型
        userCompanyRegisterRequest.setUnitType("1");
        // 统一社会信用代码
        userCompanyRegisterRequest.setCrediCode("xxx");
        // 法人名称
        userCompanyRegisterRequest.setLegalName("xxx");
        // 法人证件号
        userCompanyRegisterRequest.setLegalIdNumber("xxx");
        // 法人证件类型
        userCompanyRegisterRequest.setLegalIdType("xxx");
        // 法人电话
        userCompanyRegisterRequest.setLegalPhone("156xxxxxxxx");
        // 法人邮箱地址
        userCompanyRegisterRequest.setLegalEmail("xxx@qq.com");
        // 经办人姓名
        userCompanyRegisterRequest.setAgentName("xxx");
        // 经办人证件号
        userCompanyRegisterRequest.setAgentIdNumber("xxx");
        // 经办人证件类型
        userCompanyRegisterRequest.setAgentIdType("xxx");
        // 经办人电话
        userCompanyRegisterRequest.setAgentPhone("156xxxxxxxxx");
        // 经办人邮箱
        userCompanyRegisterRequest.setAgentEmail("xxx@qq.com");
        // 描述
        userCompanyRegisterRequest.setDescription("测试");
        UserCompanyResponse execute = signClient.execute(userCompanyRegisterRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 企业用户锁定
     */
    @Test
    public void lock() {
        UserIdentityLockRequest userIdentityLockRequest =new UserIdentityLockRequest();
        // 用户id
        userIdentityLockRequest.setUserId("10699643213195661312");
        UserCompanyLookResponse execute = signClient.execute(userIdentityLockRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 企业用户解锁
     */
    @Test
    public void unLock() {
        UserIdentityUnLockRequest userIdentityUnLockRequest = new UserIdentityUnLockRequest();
        // 用户id
        userIdentityUnLockRequest.setUserId("10699643213195661312");
        UserCompanyUnLookResponse execute = signClient.execute(userIdentityUnLockRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 用户详情
     */
    @Test
    public void userinfo() {
        CompanyUserInfoRequest companyUserInfoRequest = new CompanyUserInfoRequest();
        // 用户id
        companyUserInfoRequest.setUserId("00708331277820514304");
        CompanyUserInfoResponse execute = signClient.execute(companyUserInfoRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

}
