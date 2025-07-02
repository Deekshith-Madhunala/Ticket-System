package io.event.ticket_system.repository;

import io.event.ticket_system.entity.Event;
import io.event.ticket_system.entity.Venue;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface EventRepository extends MongoRepository<Event, String> {

    Event findFirstByVenue(Venue venue);

}
