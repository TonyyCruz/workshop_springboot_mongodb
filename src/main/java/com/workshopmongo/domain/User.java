package com.workshopmongo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Document(collection = "user")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private String id;
  private String name;
  @Indexed(unique = true)
  private String email;
  @Setter(AccessLevel.NONE)
  @DBRef(lazy = true)
  private List<Post> posts = new ArrayList<>();

  public User(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

}
