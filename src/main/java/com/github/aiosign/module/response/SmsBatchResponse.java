package com.github.aiosign.module.response;

import com.github.aiosign.base.AbstractSignResponse;
import com.github.aiosign.base.BaseSignObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author 侯存路
 * @description
 * @since 2020/8/11 8:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsBatchResponse extends AbstractSignResponse {


    private List<SmsBatchModule> data;


    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SmsBatchModule extends BaseSignObject {

        private String phone;

        private boolean result;
    }


}
