CREATE TABLE event_fields
(
    id    UUID NOT NULL,
    key   VARCHAR(255),
    value VARCHAR(255),
    CONSTRAINT pk_event_fields PRIMARY KEY (id)
);

CREATE TABLE events
(
    id         UUID                        NOT NULL,
    fields     UUID,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    record_id  UUID,
    CONSTRAINT pk_events PRIMARY KEY (id)
);

CREATE TABLE records
(
    id         UUID NOT NULL,
    is_closed  BOOLEAN,
    is_deleted BOOLEAN,
    CONSTRAINT pk_records PRIMARY KEY (id)
);

ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_FIELDS FOREIGN KEY (fields) REFERENCES event_fields (id);

ALTER TABLE events
    ADD CONSTRAINT FK_EVENTS_ON_RECORD FOREIGN KEY (record_id) REFERENCES records (id);