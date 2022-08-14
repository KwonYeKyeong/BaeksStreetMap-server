package umc.server.baeksstreetmapserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BaeksStreetMapServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaeksStreetMapServerApplication.class, args);
	}

}
