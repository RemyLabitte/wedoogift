package com.backchallenge.wedoogift.Repositories;

import com.backchallenge.wedoogift.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
