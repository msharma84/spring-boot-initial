package com.kinley.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kinley.service.StatisticsService;

@Component
public class APIFilter implements Filter {

	@Value("${publish.apiPattern}")
	private String apiPattern;
	
	@Value("${publish.serviceName}")
	private String serviceName;
	
	@Autowired
	private StatisticsService service;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		
		final HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
		String uri = httpServletRequest.getRequestURI();
		chain.doFilter(servletRequest, servletResponse);
		if(uri.matches(apiPattern)){
			service.add(serviceName, uri, httpServletRequest.getMethod());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	
}
