package lol.prankenstein;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import lol.prankenstein.repository.UserRepository;

@SpringBootApplication
@EnableMongoRepositories
public class PsUserServiceApplication {

	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PsUserServiceApplication.class, args);
	}

}
