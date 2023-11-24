package com.workshopmongo.dto;

import java.io.Serializable;
import java.time.Instant;
import com.workshopmongo.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private String id;
  private String title;
  private Instant date;
  private String body;
  private AuthorDto author;

  public PostDto(Post post) {
    id = post.getId();
    title = post.getTitle();
    date = post.getDate();
    body = post.getBody();
    author = post.getAuthor();
  }

  public static Post toPost(PostDto postDTO) {
    Post post = new Post();
    post.setId(postDTO.getId());
    post.setTitle(postDTO.getTitle());
    post.setDate(postDTO.getDate());
    post.setBody(postDTO.getBody());
    post.setAuthor(postDTO.getAuthor());
    return post;
  }
}
