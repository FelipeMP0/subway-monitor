package com.subwaymonitor.server.domains.ping;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.subwaymonitor.common.JacksonMapperFactory;
import java.time.Clock;
import java.time.Instant;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PingControllerTest {

  @MockBean private Clock clock;

  @Autowired private MockMvc mockMvc;

  @Test
  void ping_returnsOk() throws Exception {
    final ZonedDateTime currentTime = ZonedDateTime.now();
    when(clock.instant()).thenReturn(Instant.ofEpochSecond(currentTime.toEpochSecond()));
    final PingDTO expected = PingDTO.builder().currentTime(currentTime).build();
    final String expectedJson = JacksonMapperFactory.create().writer().writeValueAsString(expected);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/v1/ping"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));
  }
}
