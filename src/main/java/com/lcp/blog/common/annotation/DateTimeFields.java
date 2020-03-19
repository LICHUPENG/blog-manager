package com.lcp.blog.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author lcp
 */
@Retention(RUNTIME)
@Target({METHOD})
@Documented
@Inherited
public @interface DateTimeFields {
    String[] fields();
}
