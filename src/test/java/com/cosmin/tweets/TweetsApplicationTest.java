package com.cosmin.tweets;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = TweetsApplication.class) })
@Profile("test")
public class TweetsApplicationTest {

	public static void main(String[] args) {
		new SpringApplication(TweetsApplicationTest.class).run(args);
	}

}
