package com.workshopmongo.domain;

import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "user")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  private UUID id;
  private String name;
  private String email;
}
