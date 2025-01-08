package com.tinqin.library.reporting.apiadapter;

import com.tinqin.library.reporting.api.models.ApiError;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordInput;
import com.tinqin.library.reporting.api.operations.createrecord.CreateRecordOutput;
import com.tinqin.library.reporting.apiadapter.mappers.CreateRecordMapper;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.CreateRecord;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordInput;
import com.tinqin.library.reporting.apiadapter.operations.createrecord.ReportingCreateRecordOutput;
import io.vavr.control.Either;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiAdapterTest {

    private final UUID RECORD_ID = UUID.fromString("37c6d087-78a1-4587-bc2f-004544327827");

    @Mock
    private CreateRecordMapper createRecordMapper;

    @Mock
    private CreateRecord createRecord;

    @InjectMocks
    private ApiAdapter apiAdapter;

    @BeforeAll()
    static void init() {
        System.out.println("Starting tests for ApiAdapterTest");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Starting test");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Ending test");
    }

    @AfterAll
    static void cleanUp() {
        System.out.println("All tests done");
    }

    @Test
    void createRecordReturnsCorrect() {

        CreateRecordInput apiInput = CreateRecordInput
                .builder()
                .build();

        ReportingCreateRecordInput reportingInput = ReportingCreateRecordInput
                .builder()
                .build();

        ReportingCreateRecordOutput reportingResult = ReportingCreateRecordOutput
                .builder()
                .recordId(RECORD_ID)
                .build();

        CreateRecordOutput apiResult = CreateRecordOutput.builder()
                .recordId(RECORD_ID)
                .build();

        when(createRecordMapper.toReporting(any())).thenReturn(reportingInput);
        when(createRecord.process(any())).thenReturn(Either.right(reportingResult));
        when(createRecordMapper.toApiResult(any())).thenReturn(apiResult);

        Either<ApiError, CreateRecordOutput> record = apiAdapter.createRecord(apiInput);

        assertNotNull(record);
        assertTrue(record.isRight());
        assertEquals(RECORD_ID, record.get().getRecordId());
    }

    @Test
    void getRecord() {
    }

    @Test
    void createEvent() {
    }
}