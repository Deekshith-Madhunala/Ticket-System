package io.event.ticket_system.repository;

import io.event.ticket_system.entity.Booking;
import io.event.ticket_system.entity.Event;
import io.event.ticket_system.entity.User;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookingRepository extends MongoRepository<Booking, String> {

    Booking findFirstByUser(User user);
}
