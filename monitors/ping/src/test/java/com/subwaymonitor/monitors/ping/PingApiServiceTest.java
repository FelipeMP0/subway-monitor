package com.subwaymonitor.monitors.ping;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestClientTest
@ContextConfiguration(classes = {SpringTestConfig.class, PingApiService.class})
class PingApiServiceTest {

  private final PingApiService subject;
  private final MockRestServiceServer mockServer;

  @Autowired
  PingApiServiceTest(final PingApiService subject, final MockRestServiceServer mockServer) {
    this.subject = subject;
    this.mockServer = mockServer;
  }

  @Test
  void ping_success() throws URISyntaxException {
    mockServer
        .expect(ExpectedCount.once(), requestTo(new URI("http://example.com")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.OK));
    subject.ping();
    mockServer.verify();
  }

  @Test
  void ping_clientError_throwsException() throws URISyntaxException {
    mockServer
        .expect(ExpectedCount.once(), requestTo(new URI("http://example.com")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.BAD_REQUEST));
    Assertions.assertThrows(HttpClientErrorException.class, subject::ping);
    mockServer.verify();
  }

  @Test
  void ping_serverError_throwsException() throws URISyntaxException {
    mockServer
        .expect(ExpectedCount.once(), requestTo(new URI("http://example.com")))
        .andExpect(method(HttpMethod.GET))
        .andRespond(withStatus(HttpStatus.INTERNAL_SERVER_ERROR));
    Assertions.assertThrows(HttpServerErrorException.class, subject::ping);
    mockServer.verify();
  }
}
