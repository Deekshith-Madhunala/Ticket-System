package io.event.ticket_system.repository;

import io.event.ticket_system.entity.User;
import io.event.ticket_system.entity.Venue;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface VenueRepository extends MongoRepository<Venue, String> {

    Venue findFirstByManager(User user);

}
