package com.recruit.aspact;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用户登录拦截验证
 * 
 * @author xiejinwei
 * 
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Author {

	/**
	 * 是否需要拦截，默认为否
	 * 
	 * @return
	 */
	public boolean author() default false;
}
