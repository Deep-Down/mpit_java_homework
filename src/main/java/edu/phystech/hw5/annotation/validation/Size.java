package edu.phystech.hw5.annotation.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME) // Важно для Reflection
public @interface Size {
    int min() default 1;
    int max();
    String message() default "";
}
