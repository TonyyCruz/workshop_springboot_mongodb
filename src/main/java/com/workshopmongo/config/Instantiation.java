package com.workshopmongo.config;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.workshopmongo.domain.User;
import com.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
  @Autowired
  UserRepository repository;

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();
    User tony = new User(null, "Anthony Cruz", "Anthony@gmail.com");
    User bia = new User(null, "Bianca Alves", "bia@gmail.com");
    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");
    repository.saveAll(Arrays.asList(tony, bia, maria, alex, bob));
  }

}
