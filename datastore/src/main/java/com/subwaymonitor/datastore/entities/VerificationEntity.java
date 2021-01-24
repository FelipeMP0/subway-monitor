package com.subwaymonitor.datastore.entities;

import com.subwaymonitor.datastore.DatabaseSchemas;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "verification", catalog = DatabaseSchemas.SUBWAY_MONITOR)
public class VerificationEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @OneToMany(mappedBy = "verification", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LineStatusEntity> lineStatuses;

  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  VerificationEntity() {
    // Hibernate requires no-args constructor
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public List<LineStatusEntity> getLineStatuses() {
    return lineStatuses;
  }

  public void setLineStatuses(List<LineStatusEntity> lineStatuses) {
    this.lineStatuses = lineStatuses;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
