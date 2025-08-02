package io.event.ticket_system.entity;

import io.event.ticket_system.util.BookingStatus;
import io.event.ticket_system.util.PaymentStatus;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Document
@Getter
@Setter
public class Booking {

    private String id;

    @NotNull
    @Digits(integer = 10, fraction = 4)
    @Field(
            targetType = FieldType.DECIMAL128
    )
    private BigDecimal totalAmount;

    private Integer numberOfTickets;

    private BookingStatus bookingStatus;

    private PaymentStatus paymentStatus;

    private LocalDate bookingDate;

    private LocalDateTime cancellationDeadline;

    @DocumentReference(lazy = true)
    private User user;

    @DocumentReference(lazy = true)
    private Event event;

    @DocumentReference(lazy = true, lookup = "{ 'booking' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Payment> bookingPayments = new HashSet<>();

}