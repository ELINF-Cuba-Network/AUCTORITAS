package cu.uci.auctoritas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "cu.uci.auctoritas.repository")
public class AuctoritasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctoritasApplication.class, args);
	}


}
