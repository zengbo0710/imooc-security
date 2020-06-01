package com.imooc.security.core.validate.code.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import com.imooc.security.core.validate.code.ValidateCodeProcessor;
import com.imooc.security.core.validate.code.ValidateCodeProcessorHolder;
import com.imooc.security.core.validate.code.img.ImageCode;
import com.imooc.security.core.validate.code.sms.SmsCodeSender;

import ch.qos.logback.core.subst.Token.Type;

@RestController
public class ValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	
	@Autowired 
	private ValidateCodeGenerator imageCodeGenerator;
	
	@Autowired 
	private ValidateCodeGenerator smsCodeGenerator;
	
	@Autowired
	private SmsCodeSender smsCodeSender;
	
	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;
	
//	@Autowired
//	private Map<String, ValidateCodeGenerator> validateCodeGenerators;
	
	
	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 * 
	 * @param request
	 * @param response
	 * @param type
	 * @throws Exception
	 */
	@GetMapping("/code/{type}")
	public void createImageCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
//		Object object = validateCodeGenerators;
//		System.out.println(object);
	}
	
//	@GetMapping("/code/image")
//	public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		ImageCode imageCode = (ImageCode)imageCodeGenerator.generate(new ServletWebRequest(request));
//		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
//		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
//	}
//	
//	@GetMapping("/code/sms")
//	public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
//		ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
//		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
//		String mobile = ServletRequestUtils.getRequiredStringParameter(request, "mobile");
//		smsCodeSender.send(mobile, smsCode.getCode());
//	}
	
//	@Autowired
//	private SecurityProperties securityProperties;
	
//	private ImageCode generate(ServletWebRequest request) {
//		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth()) ;
//		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
//		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//		
//		Graphics g = image.getGraphics();
//		Random random = new Random();
//		
//		g.setColor(getRandColor(200, 250));
//		g.fillRect(0, 0, width, height);
//		g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
//		g.setColor(getRandColor(160, 200));		
//		for (int i = 0; i < 155; i++) {
//			int x = random.nextInt(width);
//			int y = random.nextInt(height);
//			int xl = random.nextInt(12);
//			int yl = random.nextInt(12);
//			g.drawLine(x, y, x + xl, y + yl);
//		}
//	
//		String sRand = "";
//		for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
//			String rand = String.valueOf(random.nextInt(10));
//			sRand += rand;
//			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
//			g.drawString(rand, 13 * i + 6, 16);
//		}
//			
//		g.dispose();
//		
//		return new ImageCode(image, sRand, securityProperties.getCode().getImage().getExpireIn());
//	}
//
//
//	private Color getRandColor(int fc, int bc) {
//		Random random = new Random();
//		if (fc > 255) {
//			fc = 255;
//		}
//		if (bc > 255) {
//			bc = 255;
//		}
//	
//		int r = fc + random.nextInt(bc - fc);
//		int g = fc + random.nextInt(bc - fc);
//		int b = fc + random.nextInt(bc - fc);
//		return new Color(r, g, b);
//	}
	
}
