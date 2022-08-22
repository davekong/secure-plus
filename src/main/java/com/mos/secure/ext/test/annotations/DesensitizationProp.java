package com.mos.secure.ext.test.annotations;

import com.mos.secure.ext.test.enums.SensitiveTypeEnum;

import java.lang.annotation.*;

/**
 * 脱敏
 *
 * @author 小尘哥
 * @date 2022/08/10
 * @version $Id: $Id
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface DesensitizationProp {

    SensitiveTypeEnum value();

    int preLength() default 0;

    int sufLength() default 0;

}
