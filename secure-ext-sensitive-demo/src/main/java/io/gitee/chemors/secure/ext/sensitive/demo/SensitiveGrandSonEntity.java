/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.sensitive.demo;

import io.gitee.chemors.secure.ext.annotations.DesensitizationProp;
import io.gitee.chemors.secure.ext.enums.SensitiveTypeEnum;
import lombok.Data;

@Data
public class SensitiveGrandSonEntity {

    @DesensitizationProp(value = SensitiveTypeEnum.EMAIL)
    private String email;
}
