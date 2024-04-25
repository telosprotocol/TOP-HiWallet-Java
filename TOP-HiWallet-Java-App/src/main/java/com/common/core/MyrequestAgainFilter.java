package com.common.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.base.core.mvc.business.CommonBusiness;
import com.base.core.mvc.web.BodyStringReaderHttpServletRequestWrapper;
import com.gitee.magic.core.utils.CheckUtils;
import com.gitee.magic.core.utils.codec.Base64;
import com.gitee.magic.framework.head.exception.BusinessException;
import com.gitee.magic.framework.head.utils.IoUtils;
import com.topnetwork.wallet.common.CodeRes;

public class MyrequestAgainFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String contentType=request.getContentType();
		checkHeaderWords(request);
        if (CommonBusiness.isDefaultContentType(contentType)) {
			ServletRequest requestWrapper = new BodyStringReaderHttpServletRequestWrapper(request);
			checkBodyWords(requestWrapper);
            filterChain.doFilter(requestWrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }
	}

	private void checkBodyWords(ServletRequest requestWrapper) throws IOException {
		String content = new String(IoUtils.inputStreamConvertBytes(requestWrapper.getInputStream(), -1));
		checkBodyJsonWords(content);
	}
	
	private void checkHeaderWords(HttpServletRequest request) {
		checkWords(request.getRequestURL().toString());
		checkWords(request.getQueryString());
		Enumeration<String> headers=request.getHeaderNames();
		while(headers.hasMoreElements()) {
		     String name=headers.nextElement();
		      checkWords(request.getHeader(name));
		}
	}

	private void checkBodyJsonWords(String content) {
		if (CheckUtils.isBase64(content)) {
			String encoding = "utf-8";
			try {
				content = new String(Base64.decode(content), encoding);
			} catch (UnsupportedEncodingException e) {
				throw new BusinessException(CodeRes.CODE_1009);
			}
		}
		checkWords(content);
	}
	
	private void checkWords(String content) {
		if (content != null && content != "" && content.toLowerCase().contains(".apk")) {
			throw new BusinessException(CodeRes.CODE_11021);
		}
	}
	

}
