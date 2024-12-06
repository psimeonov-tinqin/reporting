package com.tinqin.library.reporting.rest;

import com.tinqin.library.reporting.ApiRoutes;
import com.tinqin.library.reporting.operations.createrecord.CreateRecord;
import com.tinqin.library.reporting.operations.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.operations.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.errors.OperationError;
import com.tinqin.library.reporting.operations.getrecord.GetRecord;
import com.tinqin.library.reporting.operations.getrecord.GetRecordInput;
import com.tinqin.library.reporting.operations.getrecord.GetRecordOutput;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class RecordController extends BaseController {

  private final CreateRecord createRecord;
  private final GetRecord getRecord;

  @GetMapping(ApiRoutes.GET_RECORD)
  public ResponseEntity<?> getRecord(@PathVariable("recordId")String recordId) {

    GetRecordInput input = GetRecordInput
        .builder()
        .recordId(recordId)
        .build();

    return mapToResponseEntity(getRecord.process(input), HttpStatus.OK);
  }


 @PostMapping(ApiRoutes.POST_RECORD)
  public ResponseEntity<?> createRecord(CreateRecordInput input) {

    Either<OperationError, CreateRecordOutput> result = createRecord.process(input);

    return mapToResponseEntity(result, HttpStatus.CREATED);
  }
}
