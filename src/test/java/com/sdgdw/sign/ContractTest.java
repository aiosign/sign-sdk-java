package com.sdgdw.sign;

import com.sdgdw.sign.module.request.AsyContractIdentityRequest;
import com.sdgdw.sign.module.request.ContractAddRequest;
import com.sdgdw.sign.module.request.ContractIdentityListRequest;
import com.sdgdw.sign.module.request.ContractIdentityRequest;
import com.sdgdw.sign.module.response.ContractAddResponse;
import com.sdgdw.sign.module.response.ContractDeleteResponse;
import com.sdgdw.sign.module.response.ContractQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author yangyouwang
 * @description 合同Test
 * @since 2020/5/13
 */
@Slf4j
public class ContractTest extends AbstractSignTest {


    /*
    * 添加合同
     */
    @Test
    public void add() {
        ContractAddRequest contractAddRequest = new ContractAddRequest();
        // 合同名字
        contractAddRequest.setName("测试");
        // 文件id
        contractAddRequest.setFileId("2f5869841df7ca9095b05d05c8264980");
        // 用户id不能为空
        contractAddRequest.setUserId("00708331277820514304");
        // 描述信息
        contractAddRequest.setDescription("测试描述");
        ContractAddResponse execute = signClient.execute(contractAddRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /*
     * 查询合同信息
     */
    @Test
    public void query() {
        ContractIdentityRequest contractIdentityRequest = new ContractIdentityRequest();
        // 合同id
        contractIdentityRequest.setContractId("3c258e549c23246995ebbad5e86df974");
        ContractQueryResponse execute = signClient.execute(contractIdentityRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /*
     * 删除合同信息
     */
    @Test
    public void remove() {
        ContractIdentityListRequest contractIdentityRequest = new ContractIdentityListRequest();
        // 合同id
        contractIdentityRequest.setContractId("3c258e549c23246995ebbad5e86df974");
        ContractDeleteResponse execute = signClient.execute(contractIdentityRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }

    /*
     * 异步渲染合同
     */
    @Test
    public void render() {
        AsyContractIdentityRequest asyContractIdentityRequest = new AsyContractIdentityRequest();
        // 合同id
        asyContractIdentityRequest.setContractId("a439ccc32def0e75ec0497e6cb8499ae");
        ContractAddResponse execute = signClient.execute(asyContractIdentityRequest);
        log.info("响应状态：{}",execute.getResultCode());
        log.info("响应信息：{}",execute.getResultMessage());
        log.info("响应数据：{}",execute.getData());
    }
}
