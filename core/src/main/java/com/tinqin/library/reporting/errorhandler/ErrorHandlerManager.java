package com.tinqin.library.reporting.errorhandler;

import com.tinqin.library.reporting.errors.OperationError;
import com.tinqin.library.reporting.errorhandler.base.ErrorHandler;
import com.tinqin.library.reporting.errorhandler.base.ErrorHandlerComponent ;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ErrorHandlerManager implements ErrorHandler {
    private final ApplicationContext applicationContext;
    private List<ErrorHandlerComponent> components;

    @PostConstruct
    public void init() {
        List<ErrorHandlerComponent> discoveredComponents = applicationContext
                .getBeansOfType(ErrorHandlerComponent.class)
                .values()
                .stream()
                .toList();

        List<ErrorHandlerComponent> sortComponents = sortComponents(discoveredComponents);

        linkComponents(sortComponents);

        components = sortComponents;
    }

    private List<ErrorHandlerComponent> sortComponents(List<ErrorHandlerComponent> components) {
        return components
                .stream()
                .sorted(new InternalErrorComparator())
                .toList();
    }

    private void linkComponents(List<ErrorHandlerComponent> components) {
        for (int i = 0; i < components.size() - 1; i++) {
            components.get(i).setNext(components.get(i + 1));
        }
    }

    @Override
    public OperationError handle(Throwable throwable) {
        return components
                .getFirst()
                .handle(throwable);
    }
}
