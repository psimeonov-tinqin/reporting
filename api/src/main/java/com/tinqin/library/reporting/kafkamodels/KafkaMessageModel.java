package com.tinqin.library.reporting.kafkamodels;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class KafkaMessageModel {
  private String recordId;
  private Integer eventCount;
  private LocalDateTime timestamp;
  private String eventType;
}
