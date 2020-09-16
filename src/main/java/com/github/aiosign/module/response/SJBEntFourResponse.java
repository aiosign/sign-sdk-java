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
public class SJBEntFourResponse extends AbstractSignResponse {


    private AuthenticationAliPayCertifyModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class AuthenticationAliPayCertifyModel extends BaseSignObject {

        private String name_result;

        private String idcard_result;

        private String ent_name_result;

        private String registration_number_result;

    }


}
