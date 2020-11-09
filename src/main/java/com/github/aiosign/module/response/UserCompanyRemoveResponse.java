package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 企业用户注销返回值
 *
 * @author Administrator
 * @version 1.0
 * @date 2020/11/9 17:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserCompanyRemoveResponse extends AbstractSignResponse {
    private UserCompanyRemoveResponse.UserCompanyRemoveModule data;

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UserCompanyRemoveModule extends BaseSignObject {
        /**
         * 用户id
         */
        private String userId;

        /**
         * 锁定或解锁的结果 true操作成功 false操作失败
         */
        private boolean result;
    }
}
