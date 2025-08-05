package io.event.ticket_system.modelDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VenueDTO {

    private String id;

    @NotNull
    @Size(max = 100)
    private String venueName;

    private Integer capacity;

    @NotNull
    private String address;

    private String city;

    private String zipCode;

    private String manager; // This is the User ID of the manager
}
