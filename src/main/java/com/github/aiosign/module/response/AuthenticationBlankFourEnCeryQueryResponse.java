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
public class AuthenticationBlankFourEnCeryQueryResponse extends AbstractSignResponse {


    private AuthenticationBlankFourEnCeryQueryModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class AuthenticationBlankFourEnCeryQueryModel extends BaseSignObject {

        private String jobid;

        private String orderId;

        private String res;

        private String message;
    }


}
