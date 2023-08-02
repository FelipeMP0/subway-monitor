package com.subwaymonitor.integrations.google;

import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/** Configurations to parametrize the usage of the Google's API. */
@Component
@ConfigurationProperties(prefix = "integrations.google")
public class GoogleServicesProperties {

  private boolean enabled;
  private ServiceAccount serviceAccount;

  private SpreadsheetsConfig spreadsheetsConfig;

  public GoogleServicesProperties() {}

  public GoogleServicesProperties(
      final ServiceAccount serviceAccount, SpreadsheetsConfig spreadsheetsConfig) {
    this.serviceAccount = serviceAccount;
    this.spreadsheetsConfig = spreadsheetsConfig;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(final boolean enabled) {
    this.enabled = enabled;
  }

  public ServiceAccount getServiceAccount() {
    return serviceAccount;
  }

  public void setServiceAccount(final ServiceAccount serviceAccount) {
    this.serviceAccount = serviceAccount;
  }

  public SpreadsheetsConfig getSpreadsheetsConfig() {
    return spreadsheetsConfig;
  }

  public void setSpreadsheetsConfig(final SpreadsheetsConfig spreadsheetsConfig) {
    this.spreadsheetsConfig = spreadsheetsConfig;
  }

  static class ServiceAccount {
    private String credentials;

    public ServiceAccount() {}

    public ServiceAccount(final String credentials) {
      this.credentials = credentials;
    }

    public String getCredentials() {
      return credentials;
    }

    public void setCredentials(final String credentials) {
      this.credentials = credentials;
    }
  }

  static class SpreadsheetsConfig {

    private Map<SpreadsheetType, Spreadsheet> spreadsheets;

    public SpreadsheetsConfig() {}

    public SpreadsheetsConfig(final Map<SpreadsheetType, Spreadsheet> spreadsheets) {
      this.spreadsheets = spreadsheets;
    }

    public Map<SpreadsheetType, Spreadsheet> getSpreadsheets() {
      return spreadsheets;
    }

    public void setSpreadsheets(final Map<SpreadsheetType, Spreadsheet> spreadsheets) {
      this.spreadsheets = spreadsheets;
    }

    enum SpreadsheetType {
      VERIFICATION_ARCHIVE
    }

    static class Spreadsheet {
      private String id;

      public Spreadsheet() {}

      public Spreadsheet(final String id) {
        this.id = id;
      }

      public String getId() {
        return id;
      }

      public void setId(final String id) {
        this.id = id;
      }
    }
  }
}
