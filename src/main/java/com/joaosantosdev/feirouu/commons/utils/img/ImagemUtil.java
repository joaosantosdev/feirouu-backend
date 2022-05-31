package com.joaosantosdev.feirouu.commons.utils.img;

import org.imgscalr.Scalr;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImagemUtil {

	public static String obterExtensao(MultipartFile file) {
		String type = file.getContentType();
		type = type.substring(type.lastIndexOf("/") + 1);
		return type;
	}
	
	public static BufferedImage toBufferedReader(MultipartFile file) throws IOException {
		return  ImageIO.read(file.getInputStream());
	}
	
	public static BufferedImage pngParaJpg(MultipartFile file) {
		String ext = ImagemUtil.obterExtensao(file);

		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new RuntimeException("Image não suportada");
		}

		try {
			BufferedImage img = ImageIO.read(file.getInputStream());
			if ("png".equals(ext)) {
				img = ImagemUtil.pngParaJpg(img);
			}
			return img;
		} catch (Exception e) {
			throw new RuntimeException("Image não suportada");

		}

	}

	public static BufferedImage pngParaJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public static InputStream getInputStream(BufferedImage image, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		}catch(IOException e) {
			throw new RuntimeException("Image não suportada");
		}
	}
	
	
	public static BufferedImage cortar(BufferedImage image) {
		int min = (image.getHeight() <= image.getWidth()) ? image.getHeight() : image.getWidth();
		return Scalr.crop(image,
				(image.getWidth()/2 - min/2),
				(image.getHeight()/2 - min/2),
				min, 
				min);
	}
	
	public static BufferedImage resize(BufferedImage image, int size) {
		return Scalr.resize(image, Scalr.Method.ULTRA_QUALITY, size);
	}

}