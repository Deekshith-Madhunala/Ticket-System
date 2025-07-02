package io.event.ticket_system.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@Getter
@Setter
public class Venue {

    @Id
    private String id;

    @NotNull
    @Size(max = 100)
    private String venueName;

    private Integer capacity;

    @DocumentReference(lazy = true)
    private User manager;

    @DocumentReference(lazy = true, lookup = "{ 'venue' : ?#{#self._id} }")
    @ReadOnlyProperty
    private Set<Event> venueEvents = new HashSet<>();
}
