package horse.latte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LatteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LatteApplication.class, args);
    }

}
