package com.ctf.utils.vaildator;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoResubmitAnnotation {

    String key() default "";

    int expire() default 5;
}
