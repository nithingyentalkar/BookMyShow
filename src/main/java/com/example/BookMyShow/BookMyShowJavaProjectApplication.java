package com.example.BookMyShow;

import com.example.BookMyShow.Controllers.UserController;
import com.example.BookMyShow.dtos.SignUpRequestdto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookMyShowJavaProjectApplication implements CommandLineRunner {
	@Autowired
	private UserController userController;

	public static void main(String[] args) {
		SpringApplication.run(BookMyShowJavaProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		SignUpRequestdto signUpRequestdto = new SignUpRequestdto();
//		signUpRequestdto.setEmail("Vegeta");
//		signUpRequestdto.setPassword("Vegeta@123");
//		userController.SignUp(signUpRequestdto);


		System.out.println("userController.login(\"Vegeta\", \"Nithin\") = " + userController.login("Vegeta", "Vegeta@123"));
	}
}
