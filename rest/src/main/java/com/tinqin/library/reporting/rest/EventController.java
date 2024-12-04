package com.tinqin.library.reporting.rest;

import com.tinqin.library.reporting.ApiRoutes;
import com.tinqin.library.reporting.errors.OperationError;
import com.tinqin.library.reporting.operations.postevent.CreateEvent;
import com.tinqin.library.reporting.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.operations.postevent.CreateEventOutput;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController extends BaseController {

  private final CreateEvent createEvent;


  @PostMapping(ApiRoutes.POST_EVENT)
  public ResponseEntity<?> createEvent(CreateEventInput input) {

    Either<OperationError, CreateEventOutput> result = createEvent.process(input);

    return mapToResponseEntity(result, HttpStatus.CREATED);
  }

}
