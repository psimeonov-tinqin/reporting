package com.tinqin.library.reporting.persistence.repositories;

import com.tinqin.library.reporting.persistence.models.Event;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, UUID> {

}
