package com.tinqin.library.reporting.api.operations.postevent;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class CreateEventInput {

    @UUID
    @NotNull
    private String recordId;

    @NotNull
    @Length(min = 4, max = 6)
    private String eventName;
}
