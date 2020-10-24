package ua.cc.lajdev.common.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginDto extends UserDto {

	@Size(min = 8)
	@NotBlank
	public String password;

}
