package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.security.core.properties.SecurityProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//import com.imooc.security.app.social.AppSignUpUtils;

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
	public void regist(User user, HttpServletRequest request) {
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
		String userId = user.getUsername();
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
//		appSignUpUtils.doPostSignUp(new ServletWebRequest(request), userId);
	}
	
//	@GetMapping("/me")
//	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
//		return user;
//	}
	
	@GetMapping("/me")
	public Object getCurrentUser(Authentication user, HttpServletRequest request) throws Exception {
		
		String header = request.getHeader("Authorization");
		String token = StringUtils.substringAfter(header, "bearer ");
		
		Claims claims = Jwts.parser().setSigningKey(securityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8"))
		.parseClaimsJws(token).getBody();
		String company = (String)claims.get("company");
		logger.info("-->" + company);
		return user;
	}
	
	@PostMapping
	public User create(@Valid @RequestBody User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().stream().forEach(
					error -> System.out.println(error.getDefaultMessage()));
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}
	
	@PutMapping("/{id:\\d+}")
	@ApiOperation(value = "用户更新")
	public User update(@Valid User user,
//			@PathVariable("id") String id,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().stream().forEach(
					error -> System.out.println(error.getDefaultMessage()));
		}
		
		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());
		user.setId("1");
		return user;
	}

	@GetMapping
	@JsonView(User.UserSimpleView.class)
	public List<User> query(
//			@RequestParam(value = "username") String username, Pageable pageable
			) {	
//		System.out.println(username);
//		System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
		List<User> userList = new ArrayList<User>();
		userList.add(new User());
		userList.add(new User());
		userList.add(new User());
		return userList;
	}
	
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@ApiParam("用户id") @PathVariable("id") String id) {
//		throw new UserNotExistException(id);
		System.out.println("进入getInfo服务");
		User user = new User();
		user.setUsername("tom");
		return user;
	}
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id){
		System.out.println(id);
	}
	
}
