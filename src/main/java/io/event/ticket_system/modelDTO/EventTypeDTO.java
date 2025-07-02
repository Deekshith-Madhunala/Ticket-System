package io.event.ticket_system.modelDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventTypeDTO {

    private UUID id;

    private Integer eventTypeId;

    @NotNull
    @Size(max = 50)
    private String typeName;

    private String description;

}
