/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.util;

import cn.hutool.core.util.StrUtil;
import io.gitee.chemors.secure.ext.annotations.DesensitizationProp;
import io.gitee.chemors.secure.ext.config.SensitiveProp;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 对象属性脱敏工具类
 *
 * @author 小尘哥
 * @date 2022/09/26
 */
public class FieldSensitiveUtil {


    private static void dealList(Object o,SensitiveProp sensitiveProp) throws IllegalAccessException {
        List<Object> list = (List<Object>) o;
        for (Object obj : list) {
            dealNode(obj,sensitiveProp);
        }
    }

    private static void dealMap(Object o,SensitiveProp sensitiveProp) throws IllegalAccessException {
        Map map = (Map) o;
        Set<Map.Entry> entries=map.entrySet();
        for (Map.Entry entry:entries){
            if (entry.getValue() instanceof List){
                dealList(entry.getValue(),sensitiveProp);
            }else{
                dealNode(entry.getValue(),sensitiveProp);
            }
        }
    }

    public static void dealNode(Object o,SensitiveProp sensitiveProp) throws IllegalAccessException {
        if (o == null){
            return;
        }

        if (o instanceof List){
            dealList(o,sensitiveProp);
            return;
        }

        if (o instanceof Map){
            dealMap(o,sensitiveProp);
            return;
        }

        boolean needDepthDeal = sensitiveProp.getDepth() && !StrUtil.isBlankIfStr(sensitiveProp.getPackages());
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            String type = field.getGenericType().toString();
            Object fieldValueObj = field.get(o);
            if (fieldValueObj instanceof List){
                dealList(fieldValueObj,sensitiveProp);
            }
            // 递归子属性
            if (needDepthDeal && containType(type,sensitiveProp)){
                dealNode(fieldValueObj,sensitiveProp);
            }

            DesensitizationProp desensitizationProp = field.getAnnotation(DesensitizationProp.class);
            if (desensitizationProp == null) {
                continue;
            }
            String v = MosDesensitizedUtil.desensitizeData(fieldValueObj,desensitizationProp);
            field.set(o, v);
        }
    }

    private static Boolean containType(String type,SensitiveProp sensitiveProp){
        String[] scanPackages = sensitiveProp.getPackages().split(",");
        boolean isContainType = false;
        for (String scanPackage : scanPackages){
            if (type.contains(scanPackage)){
                isContainType = true;
                break;
            }
        }
        return isContainType;
    }
}
