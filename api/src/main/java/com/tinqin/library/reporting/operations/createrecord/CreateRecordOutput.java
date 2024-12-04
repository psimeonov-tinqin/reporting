package com.tinqin.library.reporting.operations.createrecord;

import com.tinqin.library.reporting.base.ProcessorOutput;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@ToString
@Getter
public class CreateRecordOutput implements ProcessorOutput {

  private UUID recordId;

}
