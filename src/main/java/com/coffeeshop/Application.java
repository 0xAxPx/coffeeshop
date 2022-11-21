package com.coffeeshop;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*
	Add some sql script at start
	 */
//	@Bean
//	public DataSourceInitializer dataSourceInitializer(@Qualifier("dataSource") final DataSource dataSource) {
//		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
//		resourceDatabasePopulator.addScript(new ClassPathResource("/schema.sql"));
//		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
//		dataSourceInitializer.setDataSource(dataSource);
//		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
//		return dataSourceInitializer;
//	}

	/**
	 * To initialize some test data before spring finishes its startup there're ApplicationRunner and CommandLineRunner interfaces
	 * Both interfaces have run() method:
	 *  - CommandLineRunner accepts array of String as args
	 *  - ApplicationRunner accepts spring ApplicationArguments as an argument
	 */

//	@Bean
//	ApplicationRunner init(CoffeeRepository repository) {
//		String[][] data = {
//				{"Espesso", "Test1"},
//				{"Doppio", "Test2"}
//		};
//		return args -> {
//			Stream.of(data).forEach(array -> {
//				Coffee coffee = new Coffee(array[0],array[1]);
//				repository.save(coffee);
//			});
//			repository.findAll().forEach(System.out::println);
//		};
//	}

}
