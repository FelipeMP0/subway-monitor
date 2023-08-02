package com.subwaymonitor.integrations.google;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.auth.Credentials;
import com.google.auth.http.HttpCredentialsAdapter;
import com.subwaymonitor.config.MonitorConfig;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/** Responsible for interactions with the GoogleSpreadsheet API. */
@Component
class GoogleSpreadsheetsApiService {

  private static final String APPLICATION_NAME = "Monitor";
  private static final List<String> SCOPES = List.of(SheetsScopes.SPREADSHEETS);

  private final MonitorConfig<GoogleServicesProperties> config;
  private final Sheets sheets;

  @Autowired
  GoogleSpreadsheetsApiService(
      final MonitorConfig<GoogleServicesProperties> config,
      final GoogleServicesAuthorizer authorizer,
      final NetHttpTransport netHttpTransport,
      final JsonFactory jsonFactory)
      throws IOException {
    this.config = config;
    if (config.properties().isEnabled()) {
      final Credentials credentials = authorizer.authorize(SCOPES);
      sheets =
          new Sheets.Builder(netHttpTransport, jsonFactory, new HttpCredentialsAdapter(credentials))
              .setApplicationName(APPLICATION_NAME)
              .build();
    } else {
      sheets = null;
    }
  }

  /**
   * Appends data to a spreadsheet, based on start range.
   *
   * @param spreadsheetId Identifier of the Google spreadsheet.
   * @param range Start range of the data. The values passed to the method are appended to the first
   *     empty row starting from this range.
   * @param values List of values to be added, each item of the root list is equivalent to a
   *     spreadsheet row.
   * @return A future containing the result of the operation.
   */
  CompletableFuture<Void> append(
      final String spreadsheetId, final String range, final List<List<Object>> values) {
    final var appendBody = new ValueRange().setValues(values);
    return CompletableFuture.supplyAsync(
        () -> {
          try {
            sheets
                .spreadsheets()
                .values()
                .append(spreadsheetId, range, appendBody)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("INSERT_ROWS")
                .execute();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          return null;
        },
        config.executor());
  }
}
