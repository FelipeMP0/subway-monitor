package com.subwaymonitor.server.domains.lines;

import static com.subwaymonitor.sharedmodel.StatusEnum.NORMAL_OPERATION;
import static com.subwaymonitor.sharedmodel.StatusEnum.REDUCED_SPEED;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.subwaymonitor.application.services.LineStatusService;
import com.subwaymonitor.serialization.JsonUtils;
import com.subwaymonitor.server.dto.lines.LinesCurrentStatusResponse;
import com.subwaymonitor.server.dto.shared.*;
import com.subwaymonitor.server.dto.shared.mappers.LineStatusMapper;
import com.subwaymonitor.sharedmodel.*;
import java.util.List;
import java.util.stream.Collectors;
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
class LinesControllerTest {

  private static final String LINE_1_NUMBER_IDENTIFIER = "1";
  private static final String LINE_2_NUMBER_IDENTIFIER = "2";
  private static final String COMPANY_1_SLUG = "company_1";
  private static final Line LINE_1 = new Line(LINE_1_NUMBER_IDENTIFIER, COMPANY_1_SLUG, "Line 1");
  private static final Line LINE_2 = new Line(LINE_2_NUMBER_IDENTIFIER, COMPANY_1_SLUG, "Line 2");
  private static final Status STATUS_1 = new Status(NORMAL_OPERATION, NORMAL_OPERATION.name());
  private static final Status STATUS_2 = new Status(REDUCED_SPEED, REDUCED_SPEED.name());

  @Autowired private MockMvc mockMvc;

  @MockBean private LineStatusService lineStatusService;

  @Test
  void getCurrentStatus_returnsOk() throws Exception {
    final List<LineStatus> lineStatusList =
        List.of(new LineStatus(LINE_1, STATUS_1), new LineStatus(LINE_2, STATUS_2));
    final List<LineStatusDto> lineStatusDtosList =
        lineStatusList.stream()
            .map(LineStatusMapper.INSTANCE::lineStatusToLineStatusDto)
            .collect(Collectors.toList());
    final LinesCurrentStatusResponse expected = new LinesCurrentStatusResponse(lineStatusDtosList);
    when(lineStatusService.getCurrentStatus()).thenReturn(lineStatusList);
    final String expectedJson = JsonUtils.writeValueAsString(expected);
    mockMvc
        .perform(MockMvcRequestBuilders.get("/v1/lines/currentStatus"))
        .andExpect(status().isOk())
        .andExpect(content().json(expectedJson));
  }
}