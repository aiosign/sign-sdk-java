package com.github.aiosign;

import com.github.aiosign.module.request.CertQueryRequest;
import com.github.aiosign.module.request.CertRenewalRequest;
import com.github.aiosign.module.request.UserIdentityRequest;
import com.github.aiosign.module.response.CertRenewalResponse;
import com.github.aiosign.module.response.UserCertPrepareResponse;
import com.github.aiosign.module.response.UserCertResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author yangyouwang
 * @description 证书Test
 * @since 2020/5/13
 */
@Slf4j
public class CertTest extends AbstractSignTest {

    /**
     * 证书申请
     */
    @Test
    public void apply() {
        UserIdentityRequest userIdentityRequest = new UserIdentityRequest();
        // 用户id
        userIdentityRequest.setUserId("00716661208384163840");
        UserCertPrepareResponse execute = signClient.execute(userIdentityRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 证书续期
     */
    @Test
    public void renewal() {
        CertRenewalRequest certRenewalRequest = new CertRenewalRequest();
        //用户id
        certRenewalRequest.setUserId("00716661208384163840");
        CertRenewalResponse execute = signClient.execute(certRenewalRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }

    /**
     * 证书查询
     */
    @Test
    public void certinfo() {
        CertQueryRequest certQueryRequest = new CertQueryRequest();
        // 预处理id
        certQueryRequest.setPrepareId("59ed94d642b04f2d928bc0cfdaa46a52");
        UserCertResponse execute = signClient.execute(certQueryRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
}
