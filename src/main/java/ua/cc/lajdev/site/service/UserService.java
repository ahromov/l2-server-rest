package ua.cc.lajdev.site.service;

import ua.cc.lajdev.common.dto.user.UserDto;
import ua.cc.lajdev.site.model.User;

public interface UserService {

	User findByEmail(String email);

	User findByUserName(UserDto user);

	void update(User user);

}
