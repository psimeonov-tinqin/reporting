package com.tinqin.library.reporting.processors;

import com.tinqin.library.reporting.createrecord.CreateRecord;
import com.tinqin.library.reporting.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.errors.OperationError;
import com.tinqin.library.reporting.persistence.models.Record;
import com.tinqin.library.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateRecordProcessor implements CreateRecord {

  private final ErrorHandler errorHandler;
  private final RecordRepository recordRepository;


  @Override
  public Either<OperationError, CreateRecordOutput> process(CreateRecordInput input) {
    return Try.of(() -> saveRecord(input))
        .toEither()
        .mapLeft(errorHandler::handle);
  }

  private CreateRecordOutput saveRecord(CreateRecordInput input) {
    Record record = Record
        .builder()
        .id(UUID.randomUUID())
        .isDeleted(false)
        .isClosed(false)
        .eventsList(List.of())
        .build();

    recordRepository.save(record);
    return CreateRecordOutput.builder()
        .recordId(record.getId())
        .build();
  }
}

