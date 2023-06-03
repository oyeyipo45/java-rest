package com.java_rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;


@Getter
@Setter
public class ReservationDTO {

    private Long id;

    @NotNull
    private LocalDate reservationDate;

    @NotNull
    @Schema(type = "string", example = "18:30")
    private LocalTime startTime;

    @NotNull
    @Schema(type = "string", example = "18:30")
    private LocalTime endTime;

    @NotNull
    private Long user;

}
