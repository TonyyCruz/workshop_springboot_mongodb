package com.workshopmongo.dto;

import java.io.Serializable;
import com.workshopmongo.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private String id;
  private String name;
  private String email;

  public UserDto(User user) {
    id = user.getId();
    name = user.getName();
    email = user.getEmail();
  }

  public static User toUser(UserDto userDTO) {
    User user = new User();
    user.setId(userDTO.getId());
    user.setName(userDTO.getName());
    user.setEmail(userDTO.getEmail());
    return user;
  }
}
