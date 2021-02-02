package com.github.aiosign;

import com.github.aiosign.module.request.SignLogQueryRequest;
import com.github.aiosign.module.response.SignLogQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 签署日志
 *
 * @author Administrator
 * @version 1.0
 * @date 2021/2/2 13:37
 */
@Slf4j
public class SignLogTest extends AbstractSignTest {

    /**
     * 签署日志查询
     */
    @Test
    public void query() {
        SignLogQueryRequest signLogQueryRequest=new SignLogQueryRequest("0039123c1f7c42aabf7a5f6f212e7634");
        SignLogQueryResponse execute=signClient.execute(signLogQueryRequest);
        log.info("响应状态：{}", execute.getResultCode());
        log.info("响应信息：{}", execute.getResultMessage());
        log.info("响应数据：{}", execute.getData());
    }
}
