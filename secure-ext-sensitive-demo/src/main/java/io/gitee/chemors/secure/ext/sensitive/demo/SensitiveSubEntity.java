package io.gitee.chemors.secure.ext.sensitive.demo;


import io.gitee.chemors.secure.ext.annotations.DesensitizationProp;
import io.gitee.chemors.secure.ext.enums.SensitiveTypeEnum;
import lombok.Data;

/**
 * 敏感实体
 *
 * @author 小尘哥
 * @date 2022/08/22
 */
@Data
public class SensitiveSubEntity {

    @DesensitizationProp(SensitiveTypeEnum.ADDRESS)
    private String address;

    private SensitiveGrandSonEntity sensitiveGrandSonEntity;
}
