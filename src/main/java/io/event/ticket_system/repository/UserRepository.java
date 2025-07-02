package io.event.ticket_system.repository;

import io.event.ticket_system.entity.User;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id);

    User findByEmail(String email);
}
