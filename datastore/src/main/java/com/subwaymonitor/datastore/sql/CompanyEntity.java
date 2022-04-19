package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.DatabaseSchemas;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company", catalog = DatabaseSchemas.SUBWAY_MONITOR)
class CompanyEntity {

  @Id
  @Column(name = "slug")
  private String slug;

  @Column(name = "name")
  private String name;

  @OneToMany(
      mappedBy = "company",
      fetch = FetchType.LAZY,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  private List<LineEntity> lines;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", insertable = false, updatable = false)
  private LocalDateTime updatedAt;

  CompanyEntity() {
    // Hibernate requires no-args constructor
  }

  String getSlug() {
    return slug;
  }

  void setSlug(String slug) {
    this.slug = slug;
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

  LocalDateTime getCreatedAt() {
    return createdAt;
  }

  void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
