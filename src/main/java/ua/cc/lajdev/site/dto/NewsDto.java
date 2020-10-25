package ua.cc.lajdev.site.dto;

import java.io.IOException;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import ua.cc.lajdev.common.controller.exceptions.LoadImageException;
import ua.cc.lajdev.site.model.News;

public class NewsDto {

	@NotBlank
	public String title;

	@NotBlank
	public String text;

	public MultipartFile image;

	public News toNews() {
		try {
			return new News(this.title, this.text, new Date(), this.image.getBytes());
		} catch (IOException e) {
			throw new LoadImageException();
		}
	}

}
