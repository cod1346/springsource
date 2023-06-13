package com.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.spring.interceptor.AuthCheckInterceptor;

@Configuration
public class WebConfig {
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthCheckInterceptor())
				.addPathPatterns("/member/changPwd");
	}
}
