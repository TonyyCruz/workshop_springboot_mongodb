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
import com.workshopmongo.dto.CommentDto;
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

    CommentDto comment1 = new CommentDto("Nice!!", dateFormat("2023/10/02"), new AuthorDto(bia));
    CommentDto comment2 =
        new CommentDto("Nice vacation!", dateFormat("2023/10/03"), new AuthorDto(alex));
    CommentDto comment3 =
        new CommentDto("That is true XD", dateFormat("2023/08/18"), new AuthorDto(tony));
    CommentDto comment4 =
        new CommentDto("No pain no gain.", dateFormat("2022/01/11"), new AuthorDto(bob));
    CommentDto comment5 = new CommentDto("Up", dateFormat("2022/01/11"), new AuthorDto(alex));
    post1.getComments().addAll(Arrays.asList(comment1, comment2));
    post2.getComments().add(comment3);
    post3.getComments().addAll(Arrays.asList(comment4, comment5));
    postRepository.saveAll(Arrays.asList(post1, post2, post3));
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
