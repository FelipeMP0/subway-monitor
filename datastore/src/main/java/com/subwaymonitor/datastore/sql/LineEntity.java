package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.DatabaseSchemas;
import com.subwaymonitor.sharedmodel.ImmutableLine;
import com.subwaymonitor.sharedmodel.Line;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "line", catalog = DatabaseSchemas.SUBWAY_MONITOR)
class LineEntity {

  @EmbeddedId private LineId lineId;

  @Column(name = "name")
  private String name;

  @MapsId("companySlug")
  @JoinColumn(name = "company_slug")
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
    this.lineId = new LineId(line.companyLineId(), line.companySlug());
    this.name = line.name();
  }

  Line toModel() {
    return ImmutableLine.builder()
        .companyLineId(this.lineId.companyLineId)
        .companySlug(this.lineId.companySlug)
        .name(this.name)
        .build();
  }

  LineId getLineId() {
    return lineId;
  }

  void setLineId(LineId lineId) {
    this.lineId = lineId;
  }

  String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
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

  @Embeddable
  static class LineId implements Serializable {

    @Column(name = "company_line_id")
    String companyLineId;

    @Column(name = "company_slug")
    String companySlug;

    LineId() {}

    LineId(final String companyLineId, final String companySlug) {
      this.companyLineId = companyLineId;
      this.companySlug = companySlug;
    }
  }
}
