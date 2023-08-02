package com.subwaymonitor.integrations.google;

import static com.subwaymonitor.sharedmodel.StatusEnum.NORMAL_OPERATION;
import static com.subwaymonitor.sharedmodel.StatusEnum.OPERATION_INTERRUPTED;
import static org.mockito.Mockito.when;

import com.subwaymonitor.integrations.google.GoogleServicesProperties.ServiceAccount;
import com.subwaymonitor.integrations.google.GoogleServicesProperties.SpreadsheetsConfig;
import com.subwaymonitor.integrations.google.GoogleServicesProperties.SpreadsheetsConfig.Spreadsheet;
import com.subwaymonitor.integrations.google.GoogleServicesProperties.SpreadsheetsConfig.SpreadsheetType;
import com.subwaymonitor.sharedmodel.Line;
import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GoogleSpreadsheetsArchiveServiceTest {

  private static final String SPREADSHEET_ID = UUID.randomUUID().toString();
  private GoogleSpreadsheetsArchiveService subject;
  private GoogleSpreadsheetsApiService api;

  @BeforeEach
  void setUp() {
    final var spreadsheetMap =
        Map.of(SpreadsheetType.VERIFICATION_ARCHIVE, new Spreadsheet(SPREADSHEET_ID));
    final var spreadsheetsConfig = new SpreadsheetsConfig(spreadsheetMap);
    final var serviceAccount = new ServiceAccount("credentials");
    final var properties = new GoogleServicesProperties(serviceAccount, spreadsheetsConfig);
    api = Mockito.mock(GoogleSpreadsheetsApiService.class);
    subject = new GoogleSpreadsheetsArchiveService(properties, api);
  }

  @Test
  void archiveLineStatus_success() throws ExecutionException, InterruptedException {
    final var line1 = new Line("2", "METRO_SAO_PAULO", "Verde");
    final var line2 = new Line("1", "METRO_SAO_PAULO", "Azul");
    final var status1 = new Status(NORMAL_OPERATION, "Operando normalmente");
    final var status2 = new Status(OPERATION_INTERRUPTED, "Operação interrompida");

    final List<LineStatus> lineStatuses =
        List.of(new LineStatus(line1, status1), new LineStatus(line2, status2));

    final List<List<Object>> data = convertToSpreadsheetFormat(lineStatuses);

    when(api.append(SPREADSHEET_ID, "A2", data))
        .thenReturn(CompletableFuture.completedFuture(null));

    final Void result = subject.archiveLineStatus(lineStatuses).get();

    Assertions.assertNull(result);
  }

  private List<List<Object>> convertToSpreadsheetFormat(final List<LineStatus> lineStatuses) {
    final List<List<Object>> data = new ArrayList<>();
    for (final var lineStatus : lineStatuses) {
      final List<Object> dataRow = new ArrayList<>();
      final Line line = lineStatus.line();
      dataRow.add(line.companySlug());
      dataRow.add(line.companyLineId());
      dataRow.add(line.name());
      dataRow.add(lineStatus.status().status().name());
      data.add(dataRow);
    }
    return data;
  }
}
