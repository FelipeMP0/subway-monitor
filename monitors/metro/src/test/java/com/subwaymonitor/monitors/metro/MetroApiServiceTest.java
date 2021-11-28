package com.subwaymonitor.monitors.metro;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.util.ResourceUtils;

@RestClientTest
@ContextConfiguration(classes = {SpringTestConfig.class, MetroApiService.class})
class MetroApiServiceTest {

  private final MetroApiService subject;
  private final MockRestServiceServer mockServer;

  @Autowired
  MetroApiServiceTest(final MetroApiService subject, final MockRestServiceServer mockServer) {
    this.subject = subject;
    this.mockServer = mockServer;
  }

  @Test
  void requestMetroLinesStatuses_success() throws URISyntaxException, IOException {
    final File file = ResourceUtils.getFile("classpath:metro-api/successful-response.json");
    final String responseBody = new String(Files.readAllBytes(file.toPath()));
    mockServer
        .expect(ExpectedCount.once(), requestTo(new URI("http://example.com/LineStatus")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(
            withStatus(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseBody));
    final MetroApiResponse response = subject.getStatuses();
    final MetroApiResponse expectedResponse =
        ImmutableMetroApiResponse.builder()
            .statusMetro(
                ImmutableStatusMetro.builder()
                    .dateUpdateMetro(
                        LocalDateTime.parse(
                            "09/09/2020 23:27", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .addAllLineStatuses(TestHelper.buildLineStatuses())
                    .build())
            .build();
    Assertions.assertEquals(expectedResponse, response);
    mockServer.verify();
  }
}
