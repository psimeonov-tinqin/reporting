package com.tinqin.library.reporting.apiadapter.operations.createrecord;

import com.tinqin.library.reporting.apiadapter.base.ReportingProcessorOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@AllArgsConstructor
@Builder
@ToString
@Getter
@NoArgsConstructor
public class ReportingCreateRecordOutput implements ReportingProcessorOutput {

  private UUID recordId;

}
