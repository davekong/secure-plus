package com.mos.secure.ext.test.demo;


import com.mos.secure.ext.test.annotations.DesensitizationProp;
import com.mos.secure.ext.test.enums.SensitiveTypeEnum;
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
