package com.obgames.obgamesapi.service;

import com.obgames.obgamesapi.dto.response.RelatorioResponseDTO;

public interface RelatorioService {
    
    RelatorioResponseDTO getRelatorio(Integer tipoRelatorio, long dataInicial, long dataFinal);
}
