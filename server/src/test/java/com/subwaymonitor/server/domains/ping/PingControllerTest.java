package com.subwaymonitor.server.domains.ping;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.subwaymonitor.common.JacksonMapperFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PingControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void ping_returnsOk() throws Exception {
    final PingDTO expected = PingDTO.builder().build();
    final String expectedJson = JacksonMapperFactory.create().writer().writeValueAsString(expected);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/v1/ping"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));
  }
}
