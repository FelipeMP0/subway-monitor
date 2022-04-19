package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.DatabaseSchemas;
import com.subwaymonitor.sharedmodel.LineStatus;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "line_status_history", catalog = DatabaseSchemas.SUBWAY_MONITOR)
class LineStatusEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @JoinColumn(name = "company_line_id")
  @JoinColumn(name = "company_slug")
  @ManyToOne(fetch = FetchType.LAZY)
  private LineEntity line;

  @JoinColumn(name = "status_slug")
  @ManyToOne(fetch = FetchType.LAZY)
  private StatusEntity status;

  @JoinColumn(name = "verification_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private VerificationEntity verification;

  @Column(name = "created_at", insertable = false, updatable = false)
  private ZonedDateTime createdAt;

  LineStatusEntity() {
    // Hibernate requires no-args constructor
  }

  LineStatusEntity(final LineStatus lineStatus) {
    this.line = new LineEntity(lineStatus.line());
    this.status = new StatusEntity(lineStatus.status());
  }

  LineStatus toModel() {
    return new LineStatus(this.line.toModel(), this.status.toModel());
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
