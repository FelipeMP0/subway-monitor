package com.subwaymonitor.integrations.google;

import com.subwaymonitor.integrations.google.GoogleServicesProperties.SpreadsheetsConfig;
import com.subwaymonitor.integrations.google.GoogleServicesProperties.SpreadsheetsConfig.Spreadsheet;
import com.subwaymonitor.integrations.google.GoogleServicesProperties.SpreadsheetsConfig.SpreadsheetType;
import com.subwaymonitor.sharedmodel.ArchiveService;
import com.subwaymonitor.sharedmodel.Line;
import com.subwaymonitor.sharedmodel.LineStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Implementation of the {@link ArchiveService} using Google Spreadsheets. */
@Service
public class GoogleSpreadsheetsArchiveService implements ArchiveService {

  private static final String START_RANGE = "A2";
  private final SpreadsheetsConfig config;
  private final GoogleSpreadsheetsApiService api;

  @Autowired
  public GoogleSpreadsheetsArchiveService(
      final GoogleServicesProperties properties, final GoogleSpreadsheetsApiService api) {
    this.config = properties.getSpreadsheetsConfig();
    this.api = api;
  }

  /**
   * Archives a list of {@link LineStatus}. Each item is stored as a row in a Google Spreadsheet.
   *
   * @param lineStatusList The list of items to be archived.
   * @return A future containing the result of the operation.
   */
  public CompletableFuture<Void> archiveLineStatus(final List<LineStatus> lineStatusList) {
    final Spreadsheet spreadsheet =
        config.getSpreadsheets().get(SpreadsheetType.VERIFICATION_ARCHIVE);
    final List<List<Object>> data = new ArrayList<>();
    for (final var lineStatus : lineStatusList) {
      final List<Object> dataRow = new ArrayList<>();
      final Line line = lineStatus.line();
      dataRow.add(line.companySlug());
      dataRow.add(line.companyLineId());
      dataRow.add(line.name());
      dataRow.add(lineStatus.status().status().name());
      data.add(dataRow);
    }
    return api.append(spreadsheet.getId(), START_RANGE, data);
  }
}
