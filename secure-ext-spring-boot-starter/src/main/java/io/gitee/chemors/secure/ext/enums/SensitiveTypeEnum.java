/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.enums;


/**
 * 敏感枚举类型
 *
 * @author 小尘哥
 * @version $Id: $Id
 */
public enum SensitiveTypeEnum {
    /** 中文名 */
    CHINESE_NAME,
    /** 身份证号 */
    ID_CARD,
    /** 座机号 */
    FIXED_PHONE,
    /** 手机号 */
    MOBILE_PHONE,
    /** 地址 */
    ADDRESS,
    /** 电子邮件 */
    EMAIL,
    /** 银行卡 */
    BANK_CARD,
    /** 密码 */
    PASSWORD,
    /**
     * 自定义
     */
    CUSTOM;
}
