package com.api.project.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限校验注解
 *
 * @author zwh
 */
@Target(ElementType.METHOD) // 该注解只能用于方法上
@Retention(RetentionPolicy.RUNTIME) // 在运行时保留注解信息，可以通过反射获取到注解信息
public @interface AuthCheck {

    /**
     * 有任何一个角色
     */
    String[] anyRole() default "";

    /**
     * 必须有某个角色
     */
    String mustRole() default "";

}

