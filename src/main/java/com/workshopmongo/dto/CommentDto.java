package com.workshopmongo.dto;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto implements Serializable {
  private static final long serialVersionUID = 1L;

  private String text;
  private Instant date;
  private AuthorDto author;
}
