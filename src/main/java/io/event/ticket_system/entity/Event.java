package io.event.ticket_system.entity;

import io.event.ticket_system.util.EventCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class    Event {

    @Id
    private String id;

    @NotNull
    @Size(max = 150)
    private String eventName;

    @Size(max = 1000)
    private String eventDescription;

    @NotNull
    private EventCategory eventCategory;

    @NotNull
    private String eventType;

    @NotNull
    private Instant startDateTime;

    @NotNull
    private Instant endDateTime;

    // Reference to Venue entity
    @DocumentReference(lazy = true)
    private Venue venue;

    // Ticket details as embedded objects
    private List<TicketDetail> ticketDetails;

    private String contact; // could be phone or email

    private String additionalMessage;

    // URLs to event photos
    private String eventPhotoUrl;

    private String coverPhotoUrl;

}
