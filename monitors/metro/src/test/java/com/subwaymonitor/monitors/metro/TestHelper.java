package com.subwaymonitor.monitors.metro;

import java.util.List;

class TestHelper {
  static List<MetroApiResponse.StatusMetro.LineStatus> buildLineStatuses() {
    return List.of(
        new MetroApiResponse.StatusMetro.LineStatus(
            "1", "Azul", "Linha 1 - Azul", "Linha 1", "Operação Normal", 0, ""),
        new MetroApiResponse.StatusMetro.LineStatus(
            "2", "Verde", "Linha 2 - Verde", "Linha 2", "Operação Encerrada", 0, ""),
        new MetroApiResponse.StatusMetro.LineStatus(
            "3", "Vermelha", "Linha 3 - Vermelha", "Linha 3", "Velocidade Reduzida", 0, ""),
        new MetroApiResponse.StatusMetro.LineStatus(
            "15", "Prata", "Linha 15 - Prata", "Linha 15", "Desconhecido", 0, ""));
  }
}
