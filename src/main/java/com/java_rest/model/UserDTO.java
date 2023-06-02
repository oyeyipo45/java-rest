package com.java_rest.model;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {

    private Long id;

    @NotNull
    private OffsetDateTime dateCreated;

    @NotNull
    private OffsetDateTime dateUpdated;

}
