/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import io.gitee.chemors.secure.ext.config.LogCategory;
import io.gitee.chemors.secure.ext.config.SensitiveProp;

import java.util.*;

public class LogInfoCategoryUtil {

    public static String format(String key, Object fieldValueObj, SensitiveProp sensitiveProp){

        if (fieldValueObj == null ) {
            return null;
        }
        if (!(fieldValueObj instanceof String)) {
            return String.valueOf(fieldValueObj);
        }
        String value = String.valueOf(fieldValueObj);
        List<LogCategory> categories = sensitiveProp.getLogInfo().getCategories();
        if (CollectionUtil.isEmpty(categories)){
            return value;
        }

        Optional<LogCategory> optional = categories.stream().filter(item->item.getKeywords().contains(key)).findFirst();
        if (optional.isPresent()){
            LogCategory category = optional.get();
            return StrUtil.hide(value, category.getPreLength(), value.length()-category.getSufLength());
        }else {
            return String.valueOf(fieldValueObj);
        }
    }
}
