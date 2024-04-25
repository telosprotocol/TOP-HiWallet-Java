package com.common.core;

import com.base.core.mvc.web.ExceptionController;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.base.core.mvc.web.DefaultWebMvcConfiguration;
import com.common.mvc.AuthorityInterceptor;

/**
 * Web配置
 * 
 * @author start
 *
 */
@Configuration
public class WebMVCConfiguration extends DefaultWebMvcConfiguration {

	@Primary
	@Bean
	@Override
	public WalletExceptionController exceptionController() {
		return new WalletExceptionController();
	}

	@Bean
	public AuthorityInterceptor authorityInterceptor() {
		return new AuthorityInterceptor();
	}

	@Bean
	public FilterRegistrationBean<MyrequestAgainFilter> requserAgainFilter() {
	    FilterRegistrationBean<MyrequestAgainFilter> filter = new FilterRegistrationBean<>();
	    filter.addUrlPatterns("/*");
	    filter.setFilter(new MyrequestAgainFilter());
	    return filter;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorityInterceptor()).addPathPatterns("/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedHeaders("*")
		.allowedMethods("*")
		.allowedOrigins("*")
		.maxAge(1800);
	}

}
