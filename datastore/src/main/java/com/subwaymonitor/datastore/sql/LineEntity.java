package com.subwaymonitor.datastore.sql;

import com.subwaymonitor.datastore.DatabaseSchemas;
import com.subwaymonitor.sharedmodel.Line;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
  private List<LineStatusEntity> lineStatuses;

  @Column(name = "created_at", insertable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", insertable = false, updatable = false)
  private LocalDateTime updatedAt;

  LineEntity() {
    // Hibernate requires no-args constructor
  }

  LineEntity(final Line line) {
    this.lineId = new LineId(line.companyLineId(), line.companySlug());
    this.name = line.name();
  }

  Line toModel() {
    return new Line(this.lineId.companyLineId, this.lineId.companySlug, this.name);
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

  List<LineStatusEntity> getLineStatuses() {
    return lineStatuses;
  }

  void setLineStatuses(List<LineStatusEntity> lineStatuses) {
    this.lineStatuses = lineStatuses;
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

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      LineId lineId = (LineId) o;
      return Objects.equals(companyLineId, lineId.companyLineId)
          && Objects.equals(companySlug, lineId.companySlug);
    }

    @Override
    public int hashCode() {
      return Objects.hash(companyLineId, companySlug);
    }
  }
}
