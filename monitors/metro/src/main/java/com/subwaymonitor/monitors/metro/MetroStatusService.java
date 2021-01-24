package com.subwaymonitor.monitors.metro;

import com.subwaymonitor.monitors.metro.MetroApiResponse.StatusMetro;
import com.subwaymonitor.sharedmodel.ImmutableLineStatus;
import com.subwaymonitor.sharedmodel.LineStatus;
import com.subwaymonitor.sharedmodel.SubwayStatusService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("MetroStatusService")
class MetroStatusService implements SubwayStatusService {

  private final MetroApiService metroApiService;

  @Autowired
  public MetroStatusService(final MetroApiService metroApiService) {
    this.metroApiService = metroApiService;
  }

  @Override
  public List<LineStatus> findLineStatuses() {
    final MetroApiResponse metroApiResponse = this.metroApiService.getStatuses();

    final StatusMetro statusMetro = metroApiResponse.statusMetro();

    return statusMetro
        .lineStatuses()
        .stream()
        .map(
            lineStatus ->
                ImmutableLineStatus.builder()
                    .lineNumber(Integer.parseInt(lineStatus.id()))
                    .statusSlug(lineStatus.statusDescription())
                    .build())
        .collect(Collectors.toList());
  }
}
