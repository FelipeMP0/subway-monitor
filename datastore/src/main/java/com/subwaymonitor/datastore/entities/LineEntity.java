package com.subwaymonitor.datastore.entities;

import com.subwaymonitor.datastore.DatabaseSchemas;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;

@Entity
@Table(name = "line", catalog = DatabaseSchemas.SUBWAY_MONITOR)
public class LineEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

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

  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  LineEntity() {
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

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public CompanyEntity getCompany() {
    return company;
  }

  public void setCompany(CompanyEntity company) {
    this.company = company;
  }

  public List<LineStatusEntity> getLine() {
    return line;
  }

  public void setLine(List<LineStatusEntity> line) {
    this.line = line;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(ZonedDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
