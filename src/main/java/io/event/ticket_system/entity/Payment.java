package io.event.ticket_system.entity;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;


@Document
@Getter
@Setter
public class Payment {

    private UUID id;

    private Integer paymentId;

    @NotNull
    @Size(max = 255)
    private String paymentMethod;

    private OffsetDateTime paymentDate;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Field(
            targetType = FieldType.DECIMAL128
    )
    private BigDecimal amountPaid;

    @Size(max = 255)
    private String paymentStatus;

    @DocumentReference(lazy = true)
    private Booking booking;

}
