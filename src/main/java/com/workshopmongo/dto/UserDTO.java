package com.workshopmongo.dto;

import java.io.Serializable;
import com.workshopmongo.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private String id;
  private String name;
  private String email;

  public UserDTO(User user) {
    id = user.getId();
    name = user.getName();
    email = user.getEmail();
  }
}
