package ua.cc.lajdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class L2ServerRestApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(L2ServerRestApplication.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.setAddCommandLineProperties(false);
		springApplication.run(args);

//		SpringApplication.run(L2ServerRestApplication.class, args);
	}

}
