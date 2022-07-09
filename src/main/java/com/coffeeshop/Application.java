package com.coffeeshop;

import com.coffeeshop.persistence.Coffee;
import com.coffeeshop.repository.CoffeeRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * To initialize some test data before spring finishes its startup there're ApplicationRunner and CommandLineRunner interfaces
	 * Both interfaces have run() method:
	 *  - CommandLineRunner accepts array of String as args
	 *  - ApplicationRunner accepts spring ApplicationArguments as an argument
	 */

	@Bean
	ApplicationRunner init(CoffeeRepository repository) {
		String[][] data = {
				{"Espesso", "Test1"},
				{"Doppio", "Test2"}
		};
		return args -> {
			Stream.of(data).forEach(array -> {
				Coffee coffee = new Coffee(array[0],array[1]);
				repository.save(coffee);
			});
			repository.findAll().forEach(System.out::println);
		};
	}

}
