package com.java_rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReservationsDTO {

    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    @Schema(type = "string", example = "18:30")
    private LocalTime startTime;

    @NotNull
    @Schema(type = "string", example = "18:30")
    private LocalTime endTime;

    @NotNull
    private OffsetDateTime dateCreated;

    @NotNull
    private OffsetDateTime dateUpdated;

    @NotNull
    private Long user;

}
