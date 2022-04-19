package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.DatabaseSchemas;
import com.subwaymonitor.sharedmodel.Verification;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "verification", catalog = DatabaseSchemas.SUBWAY_MONITOR)
class VerificationEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @OneToMany(
      mappedBy = "verification",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<LineStatusEntity> lineStatuses;

  @Column(name = "created_at", insertable = false, updatable = false)
  private ZonedDateTime createdAt;

  VerificationEntity() {
    // Hibernate requires no-args constructor
  }

  VerificationEntity(final Verification verification) {
    this.lineStatuses = verification.lineStatuses().stream().map(LineStatusEntity::new).toList();
    this.lineStatuses.forEach(lineStatusEntity -> lineStatusEntity.setVerification(this));
  }

  Verification toModel() {
    return new Verification(this.lineStatuses.stream().map(LineStatusEntity::toModel).toList());
  }

  UUID getId() {
    return id;
  }

  void setId(UUID id) {
    this.id = id;
  }

  List<LineStatusEntity> getLineStatuses() {
    return lineStatuses;
  }

  void setLineStatuses(List<LineStatusEntity> lineStatuses) {
    this.lineStatuses = lineStatuses;
  }

  ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
