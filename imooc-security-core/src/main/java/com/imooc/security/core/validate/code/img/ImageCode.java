package com.imooc.security.core.validate.code.img;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.imooc.security.core.validate.code.ValidateCode;

public class ImageCode extends ValidateCode {

	private BufferedImage image;

	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code, LocalDateTime.now().plusSeconds(expireIn));
		this.image = image;
	}	

	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}