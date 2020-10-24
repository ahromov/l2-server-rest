package ua.cc.lajdev.common.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class RestorePasswordDto extends UserDto {

	@Email
	@NotBlank
	public String email;

}
