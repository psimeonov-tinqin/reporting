package com.tinqin.library.reporting.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqin.library.reporting.api.ApiRoutes;
import com.tinqin.library.reporting.api.operations.postevent.CreateEventInput;
import com.tinqin.library.reporting.apiadapter.ApiAdapter;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ApiAdapter apiAdapter;


    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    @SneakyThrows
    void returnBadRequestWhenInputIsInvalid() {

        CreateEventInput input = CreateEventInput
                .builder()
                .eventName("testtest")
                .recordId("not uuid")
                .build();

//        HttpHeaders headers = new HttpHeaders() {{
//            put("Locale", List.of("en"));
//        }};

        mockMvc.perform(post(ApiRoutes.POST_EVENT)
                        .contentType(MediaType.APPLICATION_JSON)
//                .header("Locale", "en")
//                .headers(headers)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"sss", "testtest"})
    @SneakyThrows
    void returnBadRequestWhenEventNameFieldIsInvalid(String eventName) {

        CreateEventInput input = CreateEventInput
                .builder()
                .eventName(eventName)
                .recordId(UUID.randomUUID().toString())
                .build();

        mockMvc.perform(post(ApiRoutes.POST_EVENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }

    private static Stream<String> returnBadRequestWhenRecordIdFieldIsInvalid() {
        String string = UUID.randomUUID().toString();
        String invalid2 = UUID.randomUUID().toString().split("-")[3];

        return Stream.of(string, invalid2);
    }

    @ParameterizedTest
    @MethodSource
    @SneakyThrows
    void returnBadRequestWhenRecordIdFieldIsInvalid(String eventName) {

        CreateEventInput input = CreateEventInput
                .builder()
                .eventName(eventName)
                .recordId(UUID.randomUUID().toString())
                .build();

        mockMvc.perform(post(ApiRoutes.POST_EVENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isBadRequest());
    }
}