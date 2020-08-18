package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/18 9:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaiDuAiFaceCertifyVerifyResponse extends AbstractSignResponse {


    private BaiDuAiFaceCertifyVerifyModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public class BaiDuAiFaceCertifyVerifyModel extends AbstractSignResponse {

        public String score;
    }


}
