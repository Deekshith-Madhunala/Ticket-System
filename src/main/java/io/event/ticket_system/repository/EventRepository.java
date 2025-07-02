package io.event.ticket_system.repository;

import io.event.ticket_system.entity.Event;
import io.event.ticket_system.entity.EventType;
import io.event.ticket_system.entity.Venue;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EventRepository extends MongoRepository<Event, UUID> {

    Event findFirstByEventType(EventType eventType);

    Event findFirstByVenue(Venue venue);

}
