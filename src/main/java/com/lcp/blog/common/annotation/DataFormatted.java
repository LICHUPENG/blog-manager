package com.lcp.blog.common.annotation;

import com.lcp.blog.common.utils.AppFunction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @author lcp
 */
@Aspect
@Component
public class DataFormatted {
    private static String defaultDateFormat = "yyyy-MM-dd HH:mm:ss";
    private static String formatSuffix = "_formatted";

    @Around("@annotation(com.lcp.blog.common.annotation.DateTimeFields)")
    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        if (Objects.isNull(result)) {
            return null;
        }
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        DateTimeFields dateTimeFields = methodSignature.getMethod().getAnnotation(DateTimeFields.class);

        dateTimeFieldConvert(result, dateTimeFields);
        return result;
    }

    private void dateTimeFieldConvert(Object result, DateTimeFields dateTimeFields) {
        if(dateTimeFields == null){
            return;
        }

        if (dateTimeFields.fields().length == 0) {
            return;
        }

        if (Map.class.isAssignableFrom(result.getClass())) {
            _mapDateTime(result, dateTimeFields);
            return;
        }

        if (result instanceof Collection) {
            for (Object item : (Collection) result) {
                if (!Map.class.isAssignableFrom(item.getClass())) {
                    return;
                }

                _mapDateTime(item, dateTimeFields);
            }
        }
    }

    private void _mapDateTime(Object result, DateTimeFields dateTimeFields) {
        for (String field : dateTimeFields.fields()) {
            String[] fd = field.split("\\|");
            if (!((Map) result).containsKey(fd[0])) {
                continue;
            }
            String dateFormat = defaultDateFormat;
            if (fd.length == 2) {
                dateFormat = fd[1];
            }

            ((Map) result).put(fd[0] + formatSuffix, AppFunction.timeFormatted(((Map) result).get(fd[0]), dateFormat));

        }
    }
}
