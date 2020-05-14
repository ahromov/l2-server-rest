package ua.cc.lajdev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import ua.cc.lajdev.login.service.AccountService;

@SpringBootApplication
@EnableJpaAuditing
public class L2ServerWebappApplication {

	@Autowired
	static AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(L2ServerWebappApplication.class, args);
	}

}
