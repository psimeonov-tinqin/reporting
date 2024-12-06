package com.tinqin.library.reporting.apiadapter.mappers;

import com.tinqin.library.reporting.api.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventOutput;
import com.tinqin.library.reporting.apiadapter.operations.postevent.ReportingCreateEventInput;
import com.tinqin.library.reporting.apiadapter.operations.postevent.ReportingCreateEventOutput;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-06T11:26:57+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class CreateEventMapperImpl implements CreateEventMapper {

    @Override
    public ReportingCreateEventInput toReporting(CreateEventInput input) {
        if ( input == null ) {
            return null;
        }

        ReportingCreateEventInput.ReportingCreateEventInputBuilder reportingCreateEventInput = ReportingCreateEventInput.builder();

        reportingCreateEventInput.recordId( input.getRecordId() );
        reportingCreateEventInput.eventName( input.getEventName() );

        return reportingCreateEventInput.build();
    }

    @Override
    public CreateEventOutput toApiResult(ReportingCreateEventOutput output) {
        if ( output == null ) {
            return null;
        }

        CreateEventOutput.CreateEventOutputBuilder createEventOutput = CreateEventOutput.builder();

        createEventOutput.eventId( output.getEventId() );

        return createEventOutput.build();
    }
}
