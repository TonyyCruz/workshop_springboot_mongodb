package com.workshopmongo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.workshopmongo.domain.User;
import com.workshopmongo.repositories.UserRepository;
import com.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
  @Autowired
  private UserRepository repository;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(String id) {
    return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
  }
}
