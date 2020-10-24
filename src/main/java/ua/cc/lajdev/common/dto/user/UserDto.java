package ua.cc.lajdev.common.dto.user;

import javax.validation.constraints.NotBlank;

public class UserDto {

	@NotBlank
	public String login;

}
