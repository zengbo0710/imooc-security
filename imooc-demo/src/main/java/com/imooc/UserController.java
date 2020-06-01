package com.imooc;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;
//import com.imooc.security.app.social.AppSignUpUtils;
import com.imooc.security.core.properties.SecurityProperties;

@RequestMapping(value = "/user")
@RestController
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	
//	@Autowired
//	private AppSignUpUtils appSignUpUtils;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@PostMapping("/regist")
	public void regist(HttpServletRequest request) {
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		providerSignInUtils.doPostSignUp("jojo", new ServletWebRequest(request));
//		appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
	}
}
