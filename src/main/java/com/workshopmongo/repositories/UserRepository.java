package com.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
}
