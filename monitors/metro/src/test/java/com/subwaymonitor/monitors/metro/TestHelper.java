package com.subwaymonitor.monitors.metro;

import java.util.List;

class TestHelper {
  static List<MetroApiResponse.StatusMetro.LineStatus> buildLineStatuses() {
    return List.of(
        ImmutableLineStatus.builder()
            .id("1")
            .color("Azul")
            .lineFullName("Linha 1 - Azul")
            .lineNumberDescription("Linha 1")
            .statusDescription("Operação Normal")
            .statusNumber(0)
            .description("")
            .build(),
        ImmutableLineStatus.builder()
            .id("2")
            .color("Verde")
            .lineFullName("Linha 2 - Verde")
            .lineNumberDescription("Linha 2")
            .statusDescription("Operação Encerrada")
            .statusNumber(0)
            .description("")
            .build(),
        ImmutableLineStatus.builder()
            .id("3")
            .color("Vermelha")
            .lineFullName("Linha 3 - Vermelha")
            .lineNumberDescription("Linha 3")
            .statusDescription("Velocidade Reduzida")
            .statusNumber(0)
            .description("")
            .build(),
        ImmutableLineStatus.builder()
            .id("15")
            .color("Prata")
            .lineFullName("Linha 15 - Prata")
            .lineNumberDescription("Linha 15")
            .statusDescription("Desconhecido")
            .statusNumber(0)
            .description("")
            .build());
  }
}
