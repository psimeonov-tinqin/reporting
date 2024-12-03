package com.tinqin.library.reporting.persistence.repositories;

import com.tinqin.library.reporting.persistence.models.Record;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecordRepository extends JpaRepository<Record, UUID>{

}
