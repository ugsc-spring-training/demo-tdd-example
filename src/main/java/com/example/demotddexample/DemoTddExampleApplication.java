package com.example.demotddexample;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@SpringBootApplication
public class DemoTddExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTddExampleApplication.class, args);
	}

	@Bean
	@Profile("prod")
	DataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		basicDataSource.setUrl("jdbc:sqlserver://localhost:1433;database=Result");
		basicDataSource.setUsername("michal");
		basicDataSource.setPassword("password");

		return basicDataSource;
	}
}

@Component
class MyCommandLineRunner implements CommandLineRunner {

	private final ResultService resultService;

	MyCommandLineRunner(ResultService resultService) {
		this.resultService = resultService;
	}

	@Override
	public void run(String... args) throws Exception {
		TrafficLight trafficLight = resultService.getTraffiLight(2017);
		System.out.println(trafficLight);
	}
}


@Configuration
@Profile("test")
class dataSourceConfig {

	@Bean
	DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
		.setType(EmbeddedDatabaseType.H2)
		.addScript("db/schema.sql")
		.addScript("db/data.sql")
		.build();
	}
}
