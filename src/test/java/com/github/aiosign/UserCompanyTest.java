package com.github.aiosign;

import com.github.aiosign.module.request.CompanyUserInfoRequest;
import com.github.aiosign.module.request.UserCompanyRegisterRequest;
import com.github.aiosign.module.request.UserIdentityLockRequest;
import com.github.aiosign.module.request.UserIdentityUnLockRequest;
import com.github.aiosign.module.response.CompanyUserInfoResponse;
import com.github.aiosign.module.response.UserCompanyLookResponse;
import com.github.aiosign.module.response.UserCompanyResponse;
import com.github.aiosign.module.response.UserCompanyUnLookResponse;
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
        userCompanyRegisterRequest.setUserName("测试法人用户001");
        // 地区编码
        userCompanyRegisterRequest.setAreaCode("370101");
        // 单位类型
        userCompanyRegisterRequest.setUnitType("03");
        // 统一社会信用代码
        userCompanyRegisterRequest.setCrediCode("676543456789875432");
        // 法人名称
        userCompanyRegisterRequest.setLegalName("测试法人用户");
        // 法人证件号
        userCompanyRegisterRequest.setLegalIdNumber("371314288798766356");
        // 法人证件类型
        userCompanyRegisterRequest.setLegalIdType("111");
        // 法人电话
        userCompanyRegisterRequest.setLegalPhone("19876545654");
        // 法人邮箱地址
        userCompanyRegisterRequest.setLegalEmail("12388373@qq.com");
        // 经办人姓名
        userCompanyRegisterRequest.setAgentName("测试经办人");
        // 经办人证件号
        userCompanyRegisterRequest.setAgentIdNumber("326565678765465789");
        // 经办人证件类型
        userCompanyRegisterRequest.setAgentIdType("111");
        // 经办人电话
        userCompanyRegisterRequest.setAgentPhone("15609876754");
        // 经办人邮箱
        userCompanyRegisterRequest.setAgentEmail("18762873638@qq.com");
        // 描述
        userCompanyRegisterRequest.setDescription("测试企业用户001");
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
        userIdentityLockRequest.setUserId("10716662288815902720");
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
        userIdentityUnLockRequest.setUserId("10716662288815902720");
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
        companyUserInfoRequest.setUserId("10716662288815902720");
        CompanyUserInfoResponse execute = signClient.execute(companyUserInfoRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

}
