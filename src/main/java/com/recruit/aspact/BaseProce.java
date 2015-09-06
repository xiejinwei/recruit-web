package com.recruit.aspact;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recruit.entity.user.User;
import com.recruit.exception.RBCException;

@Service
@Aspect
public class BaseProce {

	@Autowired
	private HttpServletRequest request;

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object proce(ProceedingJoinPoint proce) throws Throwable {
		// 调用路径
		MethodSignature signature = (MethodSignature) proce.getSignature();
		// 取出方法
		Method method = signature.getMethod();
		String controllername = method.getDeclaringClass().getName();
		request.setAttribute("cn", controllername);
		Author author = method.getAnnotation(Author.class);
		if (author == null)
			return proce.proceed();
		boolean aut = author.author();
		if (!aut)
			return proce.proceed();
		User user = (User) request.getSession().getAttribute("suser");
		if (user == null) {
			if (controllername.indexOf("web") != -1) {
				throw new RBCException("weblogout");
			} else {
				throw new RBCException("logout");
			}
		} else {
			return proce.proceed();
		}

	}

}
