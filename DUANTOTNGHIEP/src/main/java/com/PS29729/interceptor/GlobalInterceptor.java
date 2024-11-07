package com.PS29729.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.PS29729.service.CategoryService;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
	@Autowired
	CategoryService categoryService;
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(request.getHeader("X-Requested-With") == null) {
			request.setAttribute("cates", categoryService.findAll());
		}
	}
}