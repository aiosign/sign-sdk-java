package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 8:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AuthenticationEnterpriseThreeQueryResponse extends AbstractSignResponse {


    private AuthenticationEnterpriseThreeQueryModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class AuthenticationEnterpriseThreeQueryModel extends BaseSignObject {

        private String oper_name;
        private String name;
        private Integer status;
    }


}
