package ua.cc.lajdev.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.site.model.User;
import ua.cc.lajdev.site.repo.UserRepository;
import ua.cc.lajdev.site.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserRepository repository;

	@Override
	public User findByUserName(String username) {
		return repository.findByUserName(username);
	}

	@Override
	public boolean changePassword(String username, String password) {
		User user = repository.findByUserName(username);

		if (user != null)
			user.setPassword(passwordEncoder.encode(password));

		repository.save(user);

		return true;
	}

}
