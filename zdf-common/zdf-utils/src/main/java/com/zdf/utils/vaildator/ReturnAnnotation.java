package com.zdf.utils.vaildator;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReturnAnnotation {

    Class clazz();

    NoResubmitAnnotation s() default @NoResubmitAnnotation();
}
