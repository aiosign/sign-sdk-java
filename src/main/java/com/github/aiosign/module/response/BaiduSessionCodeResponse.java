package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import com.github.aiosign.enums.EnterprisePayStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/18 9:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaiduSessionCodeResponse extends AbstractSignResponse {


    private BaiduSessionCodeModel data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class BaiduSessionCodeModel extends BaseSignObject {

        public String code;

        private String session_id;
    }

}