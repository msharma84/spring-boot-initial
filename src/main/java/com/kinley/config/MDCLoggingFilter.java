package com.kinley.config;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.kinley.model.JWTUserDetails;

@Component
public class MDCLoggingFilter implements Filter {
	
	private static final String CORRELATION_ID_HEADER = "Response_Token";
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		final HttpServletRequest httpRequest = (HttpServletRequest)request;
		final HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		final String requestHeader;
		final String responseHeader;
		
		try {
			final String token;
			
			requestHeader= httpRequest.getHeader(CORRELATION_ID_HEADER);
			responseHeader = httpResponse.getHeader(CORRELATION_ID_HEADER);
			if (requestHeader != null) {
                token = httpRequest.getHeader(requestHeader);
            } else {
                token = requestId();
            }
			
            String mdcData = String.format("[userId:%s | requestId:%s] ", user(), token);
            MDC.put("mdcData", mdcData);//Referenced from logging configuration.
            
            if (!StringUtils.isEmpty(responseHeader) || responseHeader == null) {
            	httpResponse.addHeader(CORRELATION_ID_HEADER, token);
            }
            
            chain.doFilter(httpRequest, httpResponse);
        } finally {
            MDC.clear();
        }
	}

	 private String user() {
		 	Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 	String user;
		 	if(obj instanceof JWTUserDetails){
		 		JWTUserDetails jwtUserDetails = (JWTUserDetails)obj;
		 		user = jwtUserDetails.getUsername();
		 		return user;
		 	}else{
		 		return obj.toString();
		 	}
	    }
	
	 private String requestId() {
	        return UUID.randomUUID().toString();
	    }
	 
	 @Override
		public void init(FilterConfig arg0) throws ServletException {
		}
	 
	 @Override
		public void destroy() {
		}
}