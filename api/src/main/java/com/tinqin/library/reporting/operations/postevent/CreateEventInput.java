package com.tinqin.library.reporting.operations.postevent;

import com.tinqin.library.reporting.base.ProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class CreateEventInput implements ProcessorInput {

  @UUID
  private String recordId;

  private String eventName;
}
