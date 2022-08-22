package com.mos.secure.ext.test.util;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import com.mos.secure.ext.test.annotations.DesensitizationProp;
import lombok.extern.slf4j.Slf4j;

/**
 * 脱敏工具类
 *
 * @author 小尘哥
 * @date 2022/08/22
 */
@Slf4j
public class MosDesensitizedUtil {

    public static String desensitizeData(Object obj, DesensitizationProp desensitizationProp) {
        if (obj == null || desensitizationProp == null) {
            return null;
        }
        if (!(obj instanceof String)) {
            return String.valueOf(obj);
        }
        return formatValue(obj.toString(), desensitizationProp);
    }

    private static String formatValue(String value, DesensitizationProp desensitizationProp) {
        switch (desensitizationProp.value()) {
            case ADDRESS:
                value = DesensitizedUtil.address(value, Math.min(value.length(), 3));
                break;
            case ID_CARD:
                value = DesensitizedUtil.idCardNum(value, 6, 4);
                break;
            case MOBILE_PHONE:
                value = DesensitizedUtil.mobilePhone(value);
                break;
            case EMAIL:
                value = DesensitizedUtil.email(value);
                break;
            case BANK_CARD:
                value = DesensitizedUtil.bankCard(value);
                break;
            case PASSWORD:
                value = DesensitizedUtil.password(value);
                break;
            case CHINESE_NAME:
                value = DesensitizedUtil.chineseName(value);
                break;
            case CUSTOM:
                value = StrUtil.hide(value, desensitizationProp.preLength(), Math.min(value.length(), desensitizationProp.sufLength()));
            default:
                break;

        }
        return value;
    }

}
