package com.workshopmongo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workshopmongo.domain.Post;
import com.workshopmongo.repositories.PostRepository;
import com.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
  @Autowired
  private PostRepository repository;

  public Post findById(String id) {
    return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
  }
}
