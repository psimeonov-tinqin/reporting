package com.tinqin.library.reporting.apiadapter.mappers;

import com.tinqin.library.reporting.api.models.ApiError;
import com.tinqin.library.reporting.api.models.Event;
import com.tinqin.library.reporting.api.models.Record;
import com.tinqin.library.reporting.apiadapter.errors.OperationError;
import com.tinqin.library.reporting.apiadapter.models.ReportingEvent;
import com.tinqin.library.reporting.apiadapter.models.ReportingRecord;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-06T11:26:57+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class ModelMapperImpl implements ModelMapper {

    @Override
    public ApiError toApiError(OperationError operationError) {
        if ( operationError == null ) {
            return null;
        }

        ApiError.ApiErrorBuilder apiError = ApiError.builder();

        apiError.status( operationError.getStatus() );
        apiError.errorCode( operationError.getErrorCode() );
        apiError.message( operationError.getMessage() );
        if ( operationError.getMessageLevel() != null ) {
            apiError.messageLevel( operationError.getMessageLevel().name() );
        }

        return apiError.build();
    }

    @Override
    public Event toEvent(ReportingEvent reportingEvent) {
        if ( reportingEvent == null ) {
            return null;
        }

        Event.EventBuilder event = Event.builder();

        event.id( reportingEvent.getId() );
        event.createdAt( reportingEvent.getCreatedAt() );
        event.eventName( reportingEvent.getEventName() );

        return event.build();
    }

    @Override
    public Record toRecord(ReportingRecord reportingRecord) {
        if ( reportingRecord == null ) {
            return null;
        }

        Record.RecordBuilder record = Record.builder();

        record.id( reportingRecord.getId() );
        record.isClosed( reportingRecord.getIsClosed() );
        record.isDeleted( reportingRecord.getIsDeleted() );
        record.eventsList( reportingEventListToEventList( reportingRecord.getEventsList() ) );

        return record.build();
    }

    protected List<Event> reportingEventListToEventList(List<ReportingEvent> list) {
        if ( list == null ) {
            return null;
        }

        List<Event> list1 = new ArrayList<Event>( list.size() );
        for ( ReportingEvent reportingEvent : list ) {
            list1.add( toEvent( reportingEvent ) );
        }

        return list1;
    }
}
