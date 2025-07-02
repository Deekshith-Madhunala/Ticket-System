package io.event.ticket_system.modelDTO;

import io.event.ticket_system.util.BookingStatus;
import io.event.ticket_system.util.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class BookingDTO {

    private String id;

    private BigDecimal totalAmount;

    private Integer numberOfTickets;

    private BookingStatus bookingStatus;

    private PaymentStatus paymentStatus;

    private LocalDate bookingDate;

    private LocalDateTime cancellationDeadline;

    private String userId;  // Reference by user id

    private String eventId;  // Reference by event id

    private Set<String> bookingPaymentIds; // IDs of associated payments if needed

}