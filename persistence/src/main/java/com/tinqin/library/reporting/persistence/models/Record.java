package com.tinqin.library.reporting.persistence.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "records")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(name = "is_closed")
  private Boolean isClosed;

  @Column(name = "is_deleted")
  private Boolean isDeleted;

  @OneToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER, mappedBy = "record")
  private List<Event> eventsList = new ArrayList<>();
}
