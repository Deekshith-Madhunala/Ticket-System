package io.event.ticket_system.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
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
public class Event {

    private UUID id;

    private Integer eventId;

    @NotNull
    @Size(max = 100)
    private String title;

    private String description;

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Field(
            targetType = FieldType.DECIMAL128
    )
    private BigDecimal price;

    @Size(max = 255)
    private String imageUrl;

    @DocumentReference(lazy = true)
    private EventType eventType;

    @DocumentReference(lazy = true)
    private Venue venue;

    @DocumentReference(lazy = true, lookup = "{ 'event' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Booking> eventBookings = new HashSet<>();

}
