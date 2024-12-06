package com.tinqin.library.reporting.apiadapter.mappers;

import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordOutput;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-06T11:26:57+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class CreateRecordMapperImpl implements CreateRecordMapper {

    @Override
    public ReportingCreateRecordInput toReporting(CreateRecordInput createRecordInput) {
        if ( createRecordInput == null ) {
            return null;
        }

        ReportingCreateRecordInput.ReportingCreateRecordInputBuilder reportingCreateRecordInput = ReportingCreateRecordInput.builder();

        return reportingCreateRecordInput.build();
    }

    @Override
    public CreateRecordOutput toApiResult(ReportingCreateRecordOutput output) {
        if ( output == null ) {
            return null;
        }

        CreateRecordOutput.CreateRecordOutputBuilder createRecordOutput = CreateRecordOutput.builder();

        createRecordOutput.recordId( output.getRecordId() );

        return createRecordOutput.build();
    }
}
