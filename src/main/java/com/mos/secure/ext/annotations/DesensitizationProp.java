package com.mos.secure.ext.annotations;

import com.mos.secure.ext.enums.SensitiveTypeEnum;

import java.lang.annotation.*;

/**
 * 脱敏
 *
 * @author 小尘哥
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
