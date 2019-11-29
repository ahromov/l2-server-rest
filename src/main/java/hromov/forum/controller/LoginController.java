package hromov.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hromov.forum.model.Login;
import hromov.forum.repo.AvatarRepository;
import hromov.forum.repo.LoginRepository;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class LoginController {

    @Autowired
    AvatarRepository avatarRepository;

    @Autowired
    LoginRepository loginRepository;

    @PostMapping("/account")
    public Login createComment(@Valid @RequestBody Login login) {

	return loginRepository.save(login);
    }

}
