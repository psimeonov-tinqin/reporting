package com.tinqin.library.reporting.errorhandler.base;

import com.tinqin.library.reporting.errors.OperationError;

public interface ErrorHandler {

    OperationError handle(Throwable throwable);
}
