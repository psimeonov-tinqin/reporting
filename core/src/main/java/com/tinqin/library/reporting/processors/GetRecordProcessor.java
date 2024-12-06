package com.tinqin.library.reporting.processors;

import static com.tinqin.library.reporting.ValidationMessages.RECORD_NOT_FOUND;

import com.tinqin.library.reporting.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.errors.OperationError;
import com.tinqin.library.reporting.exceptions.NotFoundException;
import com.tinqin.library.reporting.kafkaexport.KafkaProducerService;
import com.tinqin.library.reporting.kafkamodels.KafkaMessageModel;
import com.tinqin.library.reporting.operations.getrecord.GetRecord;
import com.tinqin.library.reporting.operations.getrecord.GetRecordInput;
import com.tinqin.library.reporting.operations.getrecord.GetRecordOutput;
import com.tinqin.library.reporting.persistence.models.Event;
import com.tinqin.library.reporting.persistence.models.Record;
import com.tinqin.library.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetRecordProcessor implements GetRecord {

  private final RecordRepository recordRepository;
  private final ErrorHandler errorHandler;
  private final KafkaProducerService kafkaProducerService;

  @Override
  public Either<OperationError, GetRecordOutput> process(GetRecordInput input) {
    return Try.of(() -> {
          List<Event> events = findEventsForRecord(UUID.fromString(input.getRecordId()));

          KafkaMessageModel kafkaMessage = KafkaMessageModel.builder()
              .recordId(input.getRecordId())
              .eventCount(events.size())
              .timestamp(LocalDateTime.now())
              .eventType("RECORD_ACCESSED")
              .build();

         kafkaProducerService.sendMessageSender(kafkaMessage);

          return GetRecordOutput
              .builder()
              .recordId(input.getRecordId())
              .events(events)
              .build();
        })
        .toEither()
        .mapLeft(errorHandler::handle);
  }

  private List<Event> findEventsForRecord(UUID recordId) {
    Record record = recordRepository.findById(recordId)
        .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));

    if (record.getIsDeleted()) {
      throw new NotFoundException(RECORD_NOT_FOUND);
    }
    return record.getEventsList();
  }
}


