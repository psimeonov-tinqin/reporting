package com.tinqin.library.reporting.errors;


import com.tinqin.library.reporting.enumerations.MessageLevel;
import org.springframework.http.HttpStatus;

public interface OperationError {

    HttpStatus getStatus();

    String getErrorCode();

    String getMessage();

    MessageLevel getMessageLevel();
}
