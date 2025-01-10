package com.tinqin.library.reporting.apiadapter.aspects;

import com.tinqin.library.reporting.api.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.apiadapter.enumerations.MessageLevel;
import com.tinqin.library.reporting.apiadapter.errors.BeError;
import io.vavr.control.Either;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ValidationAspectTest {

    private static ValidationAspect validationAspect;
    private final String RECORD_ID = "00000000-0000-0000-0000-000000000000";
    private final String EVENT_NAME = "testt";
    private final String INVALID_EVENT_NAME = "t";

    @BeforeAll
    static void init() {
        validationAspect = new ValidationAspect();
    }

    @BeforeEach
    void setUp() {
        validationAspect.init();
    }

    @Test
    @SneakyThrows
    void continuesWhenNoValidationErrors() {

        ProceedingJoinPoint mockJoinPoint = mock(ProceedingJoinPoint.class);

        CreateEventInput rawInput = CreateEventInput
                .builder()
                .recordId(RECORD_ID)
                .eventName(EVENT_NAME)
                .build();

        when(mockJoinPoint.getArgs()).thenReturn(new Object[]{rawInput});
        when(mockJoinPoint.proceed()).thenReturn(rawInput);

        Object validatedInput = validationAspect.validateInput(mockJoinPoint);

        assertInstanceOf(CreateEventInput.class, validatedInput);
        assertEquals(rawInput, validatedInput);
    }

    @Test
    @SneakyThrows
    void returnErrorWhenValidationErrorsPresent() {

        ProceedingJoinPoint mockJoinPoint = mock(ProceedingJoinPoint.class);

        CreateEventInput rawInput = CreateEventInput
                .builder()
                .recordId(RECORD_ID)
                .eventName(INVALID_EVENT_NAME)
                .build();

        when(mockJoinPoint.getArgs()).thenReturn(new Object[]{rawInput});

        Object validatedInput = validationAspect.validateInput(mockJoinPoint);

        assertInstanceOf(Either.class, validatedInput);

        Either<?, ?> either = (Either<?, ?>) validatedInput;

        assertTrue(either.isLeft());

        BeError errors = (BeError) either.getLeft();

        assertEquals("INVALID_INPUT", errors.getErrorCode());
        assertEquals(MessageLevel.ERROR, errors.getMessageLevel());
        assertEquals("length must be between 4 and 6", errors.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, errors.getStatus());

    }

}