package com.itany.netclass.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释注解，在类的属性上配置
 *
 * @author Thou
 * @date 2022/9/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface Comment {

    String value() default "";
}
