package io.event.ticket_system.modelDTO;

import io.event.ticket_system.util.EventCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class EventDTO {

    private String id;

    @NotNull
    @Size(max = 150)
    private String eventName;

    @Size(max = 1000)
    private String eventDescription;

    @NotNull
    private EventCategory eventCategory;

    @NotNull
    private Instant startDateTime;

    @NotNull
    private Instant endDateTime;

    private String venueId;  // Venue reference by ID

    private List<TicketDetailDTO> ticketDetails;

    private String contact; // could be phone or email

    private String additionalMessage;

    private String eventPhotoUrl;

    private String coverPhotoUrl;

}
