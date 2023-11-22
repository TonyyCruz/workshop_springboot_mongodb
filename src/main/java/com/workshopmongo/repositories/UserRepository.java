package com.workshopmongo.repositories;

import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.workshopmongo.domain.User;

public interface UserRepository extends MongoRepository<User, UUID> {
}
