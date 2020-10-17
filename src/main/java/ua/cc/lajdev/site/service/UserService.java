package ua.cc.lajdev.site.service;

import ua.cc.lajdev.site.model.User;

public interface UserService {

	public User findByUserName(String username);

	void update(User user);

}
