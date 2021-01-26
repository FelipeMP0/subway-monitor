package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.DatabaseSchemas;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company", catalog = DatabaseSchemas.SUBWAY_MONITOR)
class CompanyEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LineEntity> lines;

  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  CompanyEntity() {
    // Hibernate requires no-args constructor
  }

  UUID getId() {
    return id;
  }

  void setId(UUID id) {
    this.id = id;
  }

  String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }

  List<LineEntity> getLines() {
    return lines;
  }

  void setLines(List<LineEntity> lines) {
    this.lines = lines;
  }

  ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  void setUpdatedAt(ZonedDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
