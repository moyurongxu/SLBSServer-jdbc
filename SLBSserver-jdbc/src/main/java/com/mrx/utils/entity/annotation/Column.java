package com.mrx.utils.entity.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Column {
	
	/**
     * 字段名称
     * @return
     */
    String name();
    /**
     * 字段类型
     * @return
     */
    Class<?> type() default String.class;
    /**
     * 字段长度
     * @return
     */
    int len() default 11;
    /**
     * 是否为主键
     * @return
     */
    boolean isPrimary() default false;
    /**
     * 是否可为空
     * @return
     */
    boolean isNull() default false;
    /**
     * 是否自增长
     * @return
     */
    boolean isAutoIncrement() default false;
    /**
     * 是否可重复
     * @return
     */
    boolean isUnique() default true;
}
