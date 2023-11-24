package com.workshopmongo.dto;

import java.io.Serializable;
import com.workshopmongo.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private String id;
  private String name;

  public AuthorDto(User user) {
    id = user.getId();
    name = user.getName();
  }
}
