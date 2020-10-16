package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @since 2020/8/11 8:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationAliPayQueryResponse extends AbstractSignResponse {


    private AuthenticationAliPayQueryModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class AuthenticationAliPayQueryModel extends BaseSignObject {

        /**
         * 认证结果
         */
        private String query_result;

    }


}
