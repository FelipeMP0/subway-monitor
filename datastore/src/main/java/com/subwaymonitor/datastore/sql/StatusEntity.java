package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.DatabaseSchemas;
import com.subwaymonitor.sharedmodel.Status;
import com.subwaymonitor.sharedmodel.StatusEnum;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "status", catalog = DatabaseSchemas.SUBWAY_MONITOR)
class StatusEntity {

  @Id
  @Column(name = "slug")
  private String slug;

  @Column(name = "name")
  private String name;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "status", cascade = CascadeType.ALL)
  private List<LineStatusEntity> status;

  @Column(name = "created_at", insertable = false, updatable = false)
  private ZonedDateTime createdAt;

  StatusEntity() {
    // Hibernate requires no-args constructor
  }

  StatusEntity(final Status status) {
    this.slug = status.status().name();
    this.name = status.name();
  }

  Status toModel() {
    return new Status(StatusEnum.valueOf(this.slug), this.name);
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

  List<LineStatusEntity> getStatus() {
    return status;
  }

  void setStatus(List<LineStatusEntity> status) {
    this.status = status;
  }

  ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
