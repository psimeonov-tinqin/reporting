package com.tinqin.library.reporting.processors;

import static com.tinqin.library.reporting.ValidationMessages.IS_CLOSED;
import static com.tinqin.library.reporting.ValidationMessages.RECORD_IS_DELETED;
import static com.tinqin.library.reporting.ValidationMessages.RECORD_NOT_FOUND;

import com.tinqin.library.reporting.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.errors.OperationError;
import com.tinqin.library.reporting.exceptions.NotFoundException;
import com.tinqin.library.reporting.exceptions.RecordClosedException;
import com.tinqin.library.reporting.operations.postevent.CreateEvent;
import com.tinqin.library.reporting.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.operations.postevent.CreateEventOutput;
import com.tinqin.library.reporting.persistence.models.Event;
import com.tinqin.library.reporting.persistence.models.Record;
import com.tinqin.library.reporting.persistence.repositories.EventRepository;
import com.tinqin.library.reporting.persistence.repositories.RecordRepository;
import io.vavr.control.Either;
import io.vavr.control.Try;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateEventProcessor implements CreateEvent {


  private final ErrorHandler errorHandler;
  private final EventRepository eventRepository;
  private final RecordRepository recordRepository;

  @Override
  public Either<OperationError, CreateEventOutput> process(CreateEventInput input) {
    return Try.of(() -> {
      Record record = findRecord(input);
      String eventName = input.getEventName();
      Event event = saveEvent(record, eventName);

      return CreateEventOutput
          .builder()
          .eventId(event.getId())
          .build();
    })
        .toEither()
        .mapLeft(errorHandler::handle);
  }

  private Record findRecord(CreateEventInput input) {
    Record record = recordRepository.findById(UUID.fromString(input.getRecordId()))
        .orElseThrow(() -> new NotFoundException(RECORD_NOT_FOUND));

    if (record.getIsDeleted()) {
      throw new NotFoundException(RECORD_IS_DELETED);
    }
    if (record.getIsClosed()) {
      throw new RecordClosedException(IS_CLOSED);
    }
    return record;
  }

  private Event saveEvent(Record record,String eventName) {

    Event event = Event
        .builder()
        .createdAt(LocalDateTime.now())
        .eventName(eventName)
        .record(record)
        .build();

    eventRepository.save(event);
    record.getEventsList().add(event);
    recordRepository.save(record);

    return event;
  }
}
