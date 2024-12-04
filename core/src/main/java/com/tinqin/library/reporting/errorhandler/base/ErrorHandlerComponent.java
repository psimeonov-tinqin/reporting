package com.tinqin.library.reporting.errorhandler.base;

import com.tinqin.library.reporting.errors.OperationError;

public interface ErrorHandlerComponent {

    OperationError handle(Throwable throwable);

    ErrorHandlerComponent getNext();

    void setNext(ErrorHandlerComponent next);
}
