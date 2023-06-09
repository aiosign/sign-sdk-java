package com.github.aiosign.enums;

/**
 * @author Zhu Dunfeng
 * @date 2023/6/9 9:01
 */
public enum ContractType {

    /**
     * 合同
     */
    CONTRACT("CONTRACT"),

    /**
     * 印鉴卡
     */
    SIGNATURE_CARD("SIGNATURE-CARD");

    private String type;

    ContractType(String typeStr) {
        this.type = typeStr;
    }

    public String getType() {
        return type;
    }
}
