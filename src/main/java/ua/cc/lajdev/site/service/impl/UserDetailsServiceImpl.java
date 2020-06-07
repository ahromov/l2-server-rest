package ua.cc.lajdev.site.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.cc.lajdev.site.model.User;
import ua.cc.lajdev.site.repo.UserRepository;
import ua.cc.lajdev.site.repo.UserRolesRepository;
import ua.cc.lajdev.site.service.security.CustomUserDetails;

@Service("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);

		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username);
		} else {
			List<String> userRoles = userRolesRepository.findRoleByUserName(username);

			return new CustomUserDetails(user, userRoles);
		}
	}

}
