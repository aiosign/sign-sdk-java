package com.github.aiosign.bean;

import lombok.Data;

/**
 * 自定义签章参数
 *
 * @author modificial
 * @version $Id: $Id
 * @since 2020/4/24
 */
@Data
public class CustomSignFields extends SignFields {
    /**
     * 自定义参数，原路返回
     */
    private String customId;
}
