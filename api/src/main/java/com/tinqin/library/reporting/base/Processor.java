package com.tinqin.library.reporting.base;

import com.tinqin.library.reporting.errors.OperationError;
import io.vavr.control.Either;

public interface Processor  <R extends ProcessorInput, I extends ProcessorOutput> {

  Either<OperationError, I> process(R input);
}
