package com.tinqin.library.reporting.restexport;

import static com.tinqin.library.reporting.ApiRoutes.GET_RECORD;
import static com.tinqin.library.reporting.ApiRoutes.POST_EVENT;
import static com.tinqin.library.reporting.ApiRoutes.POST_RECORD;

import com.tinqin.library.reporting.operations.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.operations.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.operations.getrecord.GetRecordOutput;
import com.tinqin.library.reporting.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.operations.postevent.CreateEventOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "reporting")
public interface ReportingRestExport {


  @GetMapping(path = GET_RECORD, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<GetRecordOutput> getRecord(@PathVariable("recordId") String recordId);

  @PostMapping(path = POST_RECORD, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CreateRecordOutput> createRecord();

  @PostMapping(path = POST_EVENT, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CreateEventOutput> createEvent(CreateEventInput input);

}
