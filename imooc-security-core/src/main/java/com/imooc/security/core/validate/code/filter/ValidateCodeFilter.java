package com.imooc.security.core.validate.code.filter;

import java.io.IOException;
import java.net.URLStreamHandler;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.imooc.security.core.properties.SecurityConstants;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeException;
import com.imooc.security.core.validate.code.ValidateCodeProcessorHolder;
import com.imooc.security.core.validate.code.controller.ValidateCodeController;
import com.imooc.security.core.validate.code.enums.ValidateCodeType;
import com.imooc.security.core.validate.code.img.ImageCode;

@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
	
//	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 验证码校验失败处理器
	 */
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
//	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
//	private Set<String> urls = new HashSet<String>();
    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
	
	/**
	 * 系统配置信息
	 */
	@Autowired
	private SecurityProperties securityProperties;
	
	private AntPathMatcher pathMatcher = new AntPathMatcher(); 
	
    /**
     * 系统中的校验码处理器
     */
	@Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	 
//	public ValidateCodeProcessorHolder getValidateCodeProcessorHolder() {
//		return validateCodeProcessorHolder;
//	}
//
//
//	public void setValidateCodeProcessorHolder(ValidateCodeProcessorHolder validateCodeProcessorHolder) {
//		this.validateCodeProcessorHolder = validateCodeProcessorHolder;
//	}


	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
//		String url = securityProperties.getCode().getImage().getUrl();
//		if (StringUtils.isNotBlank(url)) {
//			String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(url, ",");
//			for (String configUrl : configUrls) {
//				urls.add(configUrl);
//			}
//		}
//		urls.add("/authentication/form");
		
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(), ValidateCodeType.IMAGE);
        
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
	}

	
    /**
     * 将系统中配置的需要校验验证码的URL根据校验的类型放入map
     * 
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String url, ValidateCodeType type) {
        if (StringUtils.isNotBlank(url)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(url, ",");
            for (String _url : urls) {
                    urlMap.put(_url, type);
            }
        }
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
//		boolean action = false;
//		for (String url : urls) {
//			if (pathMatcher.match(url, request.getRequestURI())) {
//				action = true;
//				break;
//			}
//		}
//		
////		if(action && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
//		if(action) {
//			try {
//				validate(new ServletWebRequest(request));
//			} catch (ValidateCodeException e) {
//				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
//				return;
//			}
//		}
		
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
//            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                .validate(new ServletWebRequest(request, response));
//                logger.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }
        
		filterChain.doFilter(request, response);
	}
	
    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     * 
     * @param request
     * @return
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }

//	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
//		ImageCode codeInSession = (ImageCode)sessionStrategy.getAttribute(request,
//				ValidateCodeController.SESSION_KEY);
//		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
//
//		if (StringUtils.isBlank(codeInRequest)) {
//			throw new ValidateCodeException("验证码的值不能为空");
//		}
//
//		if (codeInSession == null) {
//			throw new ValidateCodeException("验证码不存在");
//		}
//
//		if (codeInSession.isExpired()) {
//			sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEYSION_KEY);
//			throw new ValidateCodeException("验证码已过期");
//		}
//
//		if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
//			throw new ValidateCodeException("验证码不匹配");
//		}
//
//		sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
//	}

	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

//	public Set<String> getUrls() {
//		return urls;
//	}
//
//	public void setUrls(Set<String> urls) {
//		this.urls = urls;
//	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
}
