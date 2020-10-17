package ua.cc.lajdev.site.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.site.model.User;
import ua.cc.lajdev.site.repo.UserRepository;
import ua.cc.lajdev.site.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public User findByUserName(String username) {
		return repository.findByUserName(username);
	}

	@Override
	public void update(User user) {
		repository.save(user);
	}

}
