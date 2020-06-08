package ua.cc.lajdev.site.service;

import ua.cc.lajdev.site.model.User;

public interface UserService {

	public User findByUserName(String username);

	public boolean changePassword(String username, String password);

}
