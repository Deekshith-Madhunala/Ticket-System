package io.event.ticket_system.modelDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventDTO {

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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "75.08")
    private BigDecimal price;

    @Size(max = 255)
    private String imageUrl;

    private UUID eventType;

    private UUID venue;

}
