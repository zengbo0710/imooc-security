/**
 * 
 */
package com.imooc.security.core.validate.code.img;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.imooc.security.core.validate.code.impl.AbstractValidateCodeProcessor;

/**
 * @author Administrator
 *
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}
	
}
