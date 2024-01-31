package com.jyp_board.board_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoardAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardAppApplication.class, args);
	}

}
