package com.sdgdw.sign.bean;

import lombok.Data;
import java.io.Serializable;

/**
 * 签名域信息
 * @author modificial
 */
@Data
public class SignParam implements Serializable {
    /**
     * 用户id
     */
    private String userId;
    /**
     * 印章id
     */
    private String sealId;
    /**
     * 签名域key值
     */
    private String signKey;
    /**
     * 印章宽度，精确1位小数
     */
    private Double width;

    /**
     * 印章高度，精确1位小数
     */
    private Double height;
}