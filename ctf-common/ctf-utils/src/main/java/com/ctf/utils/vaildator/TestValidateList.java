package com.ctf.utils.vaildator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author william
 * @description
 * @Date: 2020-07-30 09:17
 */
//todo 是否可以从列表对象的属性上对列表进行查重？
@Target({ElementType.FIELD,  ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TestValidator.class)
public @interface TestValidateList {
    String message() default "格式错误";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
