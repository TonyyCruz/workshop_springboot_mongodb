package com.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import com.workshopmongo.domain.Post;
import com.workshopmongo.domain.User;
import com.workshopmongo.dto.AuthorDto;
import com.workshopmongo.repositories.PostRepository;
import com.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
  @Autowired
  UserRepository userRepository;
  @Autowired
  PostRepository postRepository;

  @Override
  public void run(String... args) throws Exception {
    userRepository.deleteAll();
    User tony = new User(null, "Anthony Cruz", "Anthony@gmail.com");
    User bia = new User(null, "Bianca Alves", "bia@gmail.com");
    User maria = new User(null, "Maria Brown", "maria@gmail.com");
    User alex = new User(null, "Alex Green", "alex@gmail.com");
    User bob = new User(null, "Bob Grey", "bob@gmail.com");
    userRepository.saveAll(Arrays.asList(tony, bia, maria, alex, bob));
    postRepository.deleteAll();
    Post post1 =
        new Post(null, "tony's post", dateFormat("2023/10/02"), "My vacation", new AuthorDto(tony));
    Post post2 =
        new Post(null, "bia's post", dateFormat("2023/08/18"), "Best summer", new AuthorDto(bia));
    Post post3 =
        new Post(null, "maria's post", dateFormat("2022/01/11"), "Gym day", new AuthorDto(maria));
    postRepository.saveAll(Arrays.asList(post1, post2, post3));
    tony.getPosts().add(post1);
    bia.getPosts().add(post2);
    maria.getPosts().add(post3);
    userRepository.saveAll(Arrays.asList(tony, bia, maria));
  }

  private Instant dateFormat(String date) {
    try {
      SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
      formater.setTimeZone(TimeZone.getTimeZone("GMT"));
      return formater.parse(date).toInstant();
    } catch (Exception e) {
      e.printStackTrace();
      return Instant.now();
    }
  }

}
