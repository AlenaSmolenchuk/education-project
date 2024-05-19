package ru.mts.educationproject.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Logging {

    String value() default "";
    boolean enter() default false;
    boolean exit() default false;
    String level() default "INFO";
    boolean logParams() default false;
    boolean logResult() default false;
}
