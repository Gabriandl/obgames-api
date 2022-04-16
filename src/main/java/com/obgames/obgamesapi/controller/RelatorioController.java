package com.obgames.obgamesapi.controller;

import com.obgames.obgamesapi.dto.response.RelatorioResponseDTO;
import com.obgames.obgamesapi.service.RelatorioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RelatorioController {
    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/relatorio")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RelatorioResponseDTO> getRelatorio(@RequestParam Integer tipoRelatorio, @RequestParam long dataInicial, @RequestParam long dataFinal  ) {
        return ResponseEntity.ok().body(relatorioService.getRelatorio(tipoRelatorio, dataInicial, dataFinal));
    }
}
