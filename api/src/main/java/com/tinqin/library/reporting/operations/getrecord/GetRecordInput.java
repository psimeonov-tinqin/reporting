package com.tinqin.library.reporting.operations.getrecord;

import com.tinqin.library.reporting.base.ProcessorInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GetRecordInput implements ProcessorInput {

  @UUID
  private String recordId;
}
