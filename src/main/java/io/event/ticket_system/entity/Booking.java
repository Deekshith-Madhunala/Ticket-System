package io.event.ticket_system.entity;

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

    private UUID id;

    private Integer bookingId;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Field(
            targetType = FieldType.DECIMAL128
    )
    private BigDecimal totalAmount;

    private Integer numberOfTickets;

    @Size(max = 255)
    private String bookingStatus;

    @Size(max = 255)
    private String paymentStatus;

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
