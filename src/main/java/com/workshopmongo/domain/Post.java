package com.workshopmongo.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.workshopmongo.dto.AuthorDto;
import com.workshopmongo.dto.CommentDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "post")
public class Post implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String id;
  private String title;
  private Instant date;
  private String body;
  private AuthorDto author;
  @Setter(AccessLevel.NONE)
  private List<CommentDto> comments = new ArrayList<>();

  public Post(String id, String title, Instant date, String body, AuthorDto author) {
    this.id = id;
    this.title = title;
    this.date = date;
    this.body = body;
    this.author = author;
  }
}
