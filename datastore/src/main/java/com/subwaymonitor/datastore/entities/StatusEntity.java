package com.subwaymonitor.datastore.entities;

import com.subwaymonitor.datastore.DatabaseSchemas;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "status", catalog = DatabaseSchemas.SUBWAY_MONITOR)
public class StatusEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "slug")
  private String slug;

  @Column(name = "name")
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
  private List<LineStatusEntity> status;

  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  StatusEntity() {
    // Hibernate requires no-args constructor
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<LineStatusEntity> getStatus() {
    return status;
  }

  public void setStatus(List<LineStatusEntity> status) {
    this.status = status;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
