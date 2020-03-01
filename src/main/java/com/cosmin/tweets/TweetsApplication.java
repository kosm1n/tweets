package com.cosmin.tweets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
public class TweetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TweetsApplication.class, args);
	}

}
