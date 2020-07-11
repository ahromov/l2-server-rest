package ua.cc.lajdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class L2ServerRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(L2ServerRestApplication.class, args);
	}

}
