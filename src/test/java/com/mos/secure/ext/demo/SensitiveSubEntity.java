package com.mos.secure.ext.demo;


import com.mos.secure.ext.enums.SensitiveTypeEnum;
import com.mos.secure.ext.annotations.DesensitizationProp;
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

}
