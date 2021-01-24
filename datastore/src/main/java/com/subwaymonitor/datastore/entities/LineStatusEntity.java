package com.subwaymonitor.datastore.entities;

import com.subwaymonitor.datastore.DatabaseSchemas;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "line_status_history", catalog = DatabaseSchemas.SUBWAY_MONITOR)
public class LineStatusEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @JoinColumn(name = "line_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private LineEntity line;

  @JoinColumn(name = "status_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private StatusEntity status;

  @ManyToOne(fetch = FetchType.LAZY)
  private VerificationEntity verification;

  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  LineStatusEntity() {
    // Hibernate requires no-args constructor
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LineEntity getLine() {
    return line;
  }

  public void setLine(LineEntity line) {
    this.line = line;
  }

  public StatusEntity getStatus() {
    return status;
  }

  public void setStatus(StatusEntity status) {
    this.status = status;
  }

  public VerificationEntity getVerification() {
    return verification;
  }

  public void setVerification(VerificationEntity verification) {
    this.verification = verification;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
