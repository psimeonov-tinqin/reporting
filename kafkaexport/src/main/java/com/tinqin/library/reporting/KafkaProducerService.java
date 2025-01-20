package com.tinqin.library.reporting;

import static com.tinqin.library.reporting.TopicConfig.TOPIC_NAME;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

  private final KafkaTemplate<String,String> kafkaTemplate;

  public void createAuthorRecord(UUID authorId){
    kafkaTemplate.send(TOPIC_NAME, authorId.toString());
  }
}
