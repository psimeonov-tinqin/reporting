package com.tinqin.library.reporting.errorhandler.components;

import com.tinqin.library.reporting.enumerations.MessageLevel ;
import com.tinqin.library.reporting.errors.BeError ;
import com.tinqin.library.reporting.errors.OperationError ;
import com.tinqin.library.reporting.exceptions.BusinessException ;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BusinessErrorHandlerComponent extends BaseErrorHandlerComponent {

    @Override
    public OperationError handle(Throwable throwable) {

        if (throwable instanceof BusinessException exception) {
            return BeError
                    .builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode("BE-001")
                    .message(exception.getMessage())
                    .messageLevel(MessageLevel.ERROR)
                    .build();
        }

        return getNext().handle(throwable);
    }
}
