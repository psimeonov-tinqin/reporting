package com.tinqin.library.reporting.operations.getrecord;

import com.tinqin.library.reporting.base.ProcessorOutput;
import com.tinqin.library.reporting.persistence.models.Event;
import com.tinqin.library.reporting.persistence.models.Record;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class GetRecordOutput implements ProcessorOutput {

  private String recordId;

  private List<Event> events;

}
