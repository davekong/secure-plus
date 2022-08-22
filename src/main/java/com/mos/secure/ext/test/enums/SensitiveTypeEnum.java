package com.mos.secure.ext.test.enums;


/**
 * 敏感枚举类型
 *
 * @author 小尘哥
 * @date 2022/08/10
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
