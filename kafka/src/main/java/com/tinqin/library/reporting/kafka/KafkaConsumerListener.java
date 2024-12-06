package com.tinqin.library.reporting.kafka;

import com.tinqin.library.reporting.kafkamodels.KafkaMessageModel;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {

  @KafkaListener(topics = {"${kafka.topic.name}"},groupId = "myGroup")
  void listener(KafkaMessageModel data) {
    System.out.println("Listener received: " + data);
  }

}
