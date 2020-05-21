package com.sdgdw.sign;

import com.sdgdw.sign.module.request.CertQueryRequest;
import com.sdgdw.sign.module.request.UserIdentityRequest;
import com.sdgdw.sign.module.response.UserCertPrepareResponse;
import com.sdgdw.sign.module.response.UserCertResponse;
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
        userIdentityRequest.setUserId("00710519812799483904");
        UserCertPrepareResponse execute = signClient.execute(userIdentityRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /**
     * 证书查询
     */
    @Test
    public void certinfo() {
        CertQueryRequest certQueryRequest = new CertQueryRequest();
        // 预处理id
        certQueryRequest.setPrepareId("cfec48ddc84c4934bfbad9b294ea2f4d");
        UserCertResponse execute = signClient.execute(certQueryRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

}
