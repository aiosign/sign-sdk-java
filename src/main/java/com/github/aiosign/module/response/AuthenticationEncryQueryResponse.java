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
public class AuthenticationEncryQueryResponse extends AbstractSignResponse {


    private AuthenticationEncryQueryModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class AuthenticationEncryQueryModel extends BaseSignObject {
        /**
         * 名字
         */
        private String name;
        /**
         * 身份证号
         */
        private String id_card;
        /**
         * 手机号
         */
        private String phone;
        /**
         * 状态
         */
        private String res;
        /**
         * 结果信息
         */
        private String resmsg;
        /**
         * 运营商
         */
        private String type;
        /**
         * 省份
         */
        private String province;
        /**
         * 市区
         */
        private String city;
        /**
         * 详情码
         */
        private String rescode;

    }


}
