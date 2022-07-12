package com.itransition.annotation;

import org.aspectj.lang.annotation.Before;

import java.lang.annotation.*;

/**
 * @author Bekjon Bakhromov
 * @since 10.07.2022
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPermission {

    String value();

    
}
