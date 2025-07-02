package io.event.ticket_system.modelDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketDetailDTO {

    @NotNull
    @Size(max = 100)
    private String ticketName;

    @Min(0)
    private Integer ticketQuantity;

    @Min(0)
    private Double ticketPrice;

    @Size(max = 50)
    private String ticketPriceDetails;

}
