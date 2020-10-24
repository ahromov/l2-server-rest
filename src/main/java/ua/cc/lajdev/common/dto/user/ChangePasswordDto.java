package ua.cc.lajdev.common.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangePasswordDto extends UserDto {

	@Size(min = 8)
	@NotBlank
	public String password;

	@Size(min = 8)
	@NotBlank
	public String newPassword;

	@Size(min = 8)
	@NotBlank
	public String newRepeatedPassword;

}
