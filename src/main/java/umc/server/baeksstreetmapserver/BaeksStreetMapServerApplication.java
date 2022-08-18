package umc.server.baeksstreetmapserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.server.baeksstreetmapserver.common.query.DummyKeywordQuery;
import umc.server.baeksstreetmapserver.common.query.DummyStoreQuery;

@EnableJpaAuditing
@SpringBootApplication
public class BaeksStreetMapServerApplication implements CommandLineRunner {

	private final DummyStoreQuery dummyStoreQuery;
	private final DummyKeywordQuery dummyKeywordQuery;

	public BaeksStreetMapServerApplication(DummyStoreQuery dummyStoreQuery, DummyKeywordQuery dummyKeywordQuery) {
		this.dummyStoreQuery = dummyStoreQuery;
		this.dummyKeywordQuery = dummyKeywordQuery;
	}

	public static void main(String[] args) {
		SpringApplication.run(BaeksStreetMapServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dummyStoreQuery.insertExcel();
		dummyKeywordQuery.insertExcel();
	}

}
