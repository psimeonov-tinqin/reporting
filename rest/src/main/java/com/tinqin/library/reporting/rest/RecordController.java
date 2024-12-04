package com.tinqin.library.reporting.rest;

import com.tinqin.library.reporting.ApiRoutes;
import com.tinqin.library.reporting.createrecord.CreateRecord;
import com.tinqin.library.reporting.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.errors.OperationError;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor

public class RecordController extends BaseController {

  private final CreateRecord createRecord;


  @PostMapping(ApiRoutes.POST_RECORD)
  public ResponseEntity<?> createRecord(CreateRecordInput input) {

    Either<OperationError, CreateRecordOutput> result = createRecord.process(input);

    return mapToResponseEntity(result, HttpStatus.CREATED);
  }
}
