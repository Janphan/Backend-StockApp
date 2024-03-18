package haagahelia.fi.stockapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import haagahelia.fi.stockapp.domain.AppUser;
import haagahelia.fi.stockapp.domain.AppUserRepository;
import haagahelia.fi.stockapp.domain.Category;
import haagahelia.fi.stockapp.domain.CategoryRepository;
import haagahelia.fi.stockapp.domain.Stock;
import haagahelia.fi.stockapp.domain.StockRepository;

@SpringBootApplication
public class StockappApplication {
	private static final Logger log = LoggerFactory.getLogger(StockappApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StockappApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(StockRepository srepository, CategoryRepository crepository,
			AppUserRepository urepository) {
		return (args) -> {
			log.info("save a couple of stocks");
			// save some categories
			crepository.save(new Category("Dry"));
			crepository.save(new Category("Medicine"));
			crepository.save(new Category("Spice"));
			// save stocks
			srepository.save(new Stock("Rice", "20.06.2024", 25, "Thailan premium rice", 40,
					crepository.findByName("Dry").get(0)));
			srepository.save(new Stock("Fish sauce", "15.06.2025", 1, "Squid fish sauce", 2.56,
					crepository.findByName("Spice").get(0)));
			srepository.save(new Stock("Vitamin B12", "04.07.2025", 1, "Vitamin B12 - VitaTabs 1000microgram", 7.99,
					crepository.findByName("Medicine").get(0)));

			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
					"user1@gmail.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C",
					"user2@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			log.info("fetch all stocks");
			for (Stock stock : srepository.findAll()) {
				log.info(stock.toString());
			}
		};
	}
}
