package com.subwaymonitor.datastore;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "line_status_history", catalog = DatabaseSchemas.SUBWAY_MONITOR)
class LineStatusEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "line_id")
  private UUID lineId;

  @Column(name = "status_id")
  private UUID statusId;

  @Column(name = "verification_number")
  private Integer verificationNumber;

  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getLineId() {
    return lineId;
  }

  public void setLineId(UUID lineId) {
    this.lineId = lineId;
  }

  public UUID getStatusId() {
    return statusId;
  }

  public void setStatusId(UUID statusId) {
    this.statusId = statusId;
  }

  public Integer getVerificationNumber() {
    return verificationNumber;
  }

  public void setVerificationNumber(Integer verificationNumber) {
    this.verificationNumber = verificationNumber;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
