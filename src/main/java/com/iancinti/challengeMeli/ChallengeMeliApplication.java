package com.iancinti.challengeMeli;

import com.iancinti.challengeMeli.config.RedisConfig;
import com.iancinti.challengeMeli.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({RedisConfig.class, WebConfig.class})
public class ChallengeMeliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeMeliApplication.class, args);
	}

}
