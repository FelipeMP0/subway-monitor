package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.DatabaseSchemas;
import com.subwaymonitor.sharedmodel.ImmutableLine;
import com.subwaymonitor.sharedmodel.Line;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "line", catalog = DatabaseSchemas.SUBWAY_MONITOR)
class LineEntity {

  @Id
  @Column(name = "slug")
  private String slug;

  @Column(name = "name")
  private String name;

  @Column(name = "number")
  private Integer number;

  @ManyToOne(fetch = FetchType.LAZY)
  private CompanyEntity company;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "line", cascade = CascadeType.ALL)
  private List<LineStatusEntity> line;

  @Column(name = "created_at", insertable = false, updatable = false)
  private ZonedDateTime createdAt;

  @Column(name = "updated_at", insertable = false, updatable = false)
  private ZonedDateTime updatedAt;

  LineEntity() {
    // Hibernate requires no-args constructor
  }

  LineEntity(final Line line) {
    this.slug = line.slug();
    this.name = line.name();
    this.number = line.number();
  }

  Line toModel() {
    return ImmutableLine.builder().slug(this.slug).name(this.name).number(this.number).build();
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

  Integer getNumber() {
    return number;
  }

  void setNumber(Integer number) {
    this.number = number;
  }

  CompanyEntity getCompany() {
    return company;
  }

  void setCompany(CompanyEntity company) {
    this.company = company;
  }

  List<LineStatusEntity> getLine() {
    return line;
  }

  void setLine(List<LineStatusEntity> line) {
    this.line = line;
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
