package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.DatabaseSchemas;
import com.subwaymonitor.sharedmodel.ImmutableLineStatus;
import com.subwaymonitor.sharedmodel.LineStatus;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "line_status_history", catalog = DatabaseSchemas.SUBWAY_MONITOR)
class LineStatusEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @JoinColumn(name = "line_slug")
  @ManyToOne(fetch = FetchType.LAZY)
  private LineEntity line;

  @JoinColumn(name = "status_slug")
  @ManyToOne(fetch = FetchType.LAZY)
  private StatusEntity status;

  @ManyToOne(fetch = FetchType.LAZY)
  private VerificationEntity verification;

  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  LineStatusEntity() {
    // Hibernate requires no-args constructor
  }

  LineStatusEntity(final LineStatus lineStatus) {
    this.line = new LineEntity(lineStatus.line());
    this.status = new StatusEntity(lineStatus.status());
  }

  LineStatus toModel() {
    return ImmutableLineStatus.builder()
        .line(this.line.toModel())
        .status(this.status.toModel())
        .build();
  }

  UUID getId() {
    return id;
  }

  void setId(UUID id) {
    this.id = id;
  }

  LineEntity getLine() {
    return line;
  }

  void setLine(LineEntity line) {
    this.line = line;
  }

  StatusEntity getStatus() {
    return status;
  }

  void setStatus(StatusEntity status) {
    this.status = status;
  }

  VerificationEntity getVerification() {
    return verification;
  }

  void setVerification(VerificationEntity verification) {
    this.verification = verification;
  }

  ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
