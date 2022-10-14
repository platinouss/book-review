package com.platinouss.bookrecommend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BookRecommendApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookRecommendApplication.class, args);
	}

}
