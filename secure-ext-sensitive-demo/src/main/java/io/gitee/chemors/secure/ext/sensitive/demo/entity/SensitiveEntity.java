/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.sensitive.demo.entity;


import io.gitee.chemors.secure.ext.annotations.DesensitizationProp;
import io.gitee.chemors.secure.ext.enums.SensitiveTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * 敏感实体
 *
 * @author 小尘哥
 * @date 2022/08/22
 */
@Data
public class SensitiveEntity {

    private String id;

    @DesensitizationProp(value = SensitiveTypeEnum.CUSTOM,preLength = 1,sufLength = 5)
    private String name;

    @DesensitizationProp(SensitiveTypeEnum.MOBILE_PHONE)
    private String mobile;


    @DesensitizationProp(SensitiveTypeEnum.ID_CARD)
    private String idCard;

    private List<SensitiveSubEntity> subEntities;

}
