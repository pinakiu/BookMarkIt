package com.bookTrackerProject.bookMarkIt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookTrackerProject.bookMarkIt.repositories.UserRepository;

@SpringBootApplication
public class BookMarkItApplication implements CommandLineRunner {
	private final UserRepository userRepository;

    public BookMarkItApplication(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
		SpringApplication.run(BookMarkItApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		userRepository.findAll().forEach(System.out::println);
	}

}
