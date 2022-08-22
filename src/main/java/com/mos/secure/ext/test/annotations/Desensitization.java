package com.mos.secure.ext.test.annotations;

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
public @interface Desensitization {

    boolean enable() default true;
}
