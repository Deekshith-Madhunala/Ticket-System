package io.event.ticket_system.repository;

import io.event.ticket_system.entity.Booking;
import io.event.ticket_system.entity.Payment;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PaymentRepository extends MongoRepository<Payment, UUID> {

    Payment findFirstByBooking(Booking booking);

}
