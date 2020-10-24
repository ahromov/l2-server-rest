package ua.cc.lajdev.common.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MessageDto extends UserDto {

	@Size(min = 20)
	@NotBlank
	public String message;

}
