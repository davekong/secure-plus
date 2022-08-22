package com.mos.secure.ext.annotations;

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
public @interface Desensitization {

    boolean enable() default true;
}
