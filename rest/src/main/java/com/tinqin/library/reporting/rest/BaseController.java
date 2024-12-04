package com.tinqin.library.reporting.rest;


import com.tinqin.library.reporting.base.ProcessorOutput;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.tinqin.library.reporting.errors.OperationError;

public class BaseController {
  public BaseController() {

  }

  protected <O extends ProcessorOutput> ResponseEntity<?> mapToResponseEntity(
      Either<OperationError, O> either, HttpStatus httpStatus) {

    return either.isRight() ? new ResponseEntity((ProcessorOutput) either.get(), httpStatus)
        : new ResponseEntity(((OperationError) either.getLeft()).getStatus(), httpStatus);
  }
}
