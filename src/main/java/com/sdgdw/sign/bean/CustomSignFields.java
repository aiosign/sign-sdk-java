package com.sdgdw.sign.bean;

import lombok.Data;

/**
 * 自定义签章参数
 * @author modificial
 * @description
 * @since 2020/4/24
 */
@Data
public class CustomSignFields extends SignFields {
    /**
     * 自定义参数，原路返回
     */
    private String customId;
}
