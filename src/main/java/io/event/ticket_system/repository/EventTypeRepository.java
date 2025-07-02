package io.event.ticket_system.repository;

import io.event.ticket_system.entity.EventType;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EventTypeRepository extends MongoRepository<EventType, UUID> {
}
