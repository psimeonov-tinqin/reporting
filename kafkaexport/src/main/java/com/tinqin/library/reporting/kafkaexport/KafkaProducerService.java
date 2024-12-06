package com.tinqin.library.reporting.kafkaexport;

import com.tinqin.library.reporting.kafkamodels.KafkaMessageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

  private final KafkaTemplate<String, KafkaMessageModel> kafkaTemplate;

  @Value("${kafka.topic.name}")
  private String topicName;

  public void sendRecordAccessedEvent(String key, KafkaMessageModel kafkaMessageModel) {
    kafkaTemplate.send(topicName, key, kafkaMessageModel);
    System.out.println("Sent Kafka Message: " + kafkaMessageModel);
  }

  public void sendMessageSender(KafkaMessageModel kafkaMessageModel) {
    Message<KafkaMessageModel> message = MessageBuilder
        .withPayload(kafkaMessageModel)
        .setHeader(KafkaHeaders.TOPIC, topicName)
        .build();
    kafkaTemplate.send(message);
  }

}
