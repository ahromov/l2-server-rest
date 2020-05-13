package ua.cc.lajdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class L2ServerWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(L2ServerWebappApplication.class, args);
	}

}
