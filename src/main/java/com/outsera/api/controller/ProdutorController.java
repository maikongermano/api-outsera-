package com.outsera.api.controller;

import com.outsera.api.model.ResultadoIntervaloPremiacao;
import com.outsera.api.usecase.IntervaloPremiacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Produtores", description = "Endpoints relacionados aos intervalos de premiação")
@RestController
@RequestMapping("/produtores")
@RequiredArgsConstructor
public class ProdutorController {

    private final IntervaloPremiacaoService service;
    
    @Operation(summary = "Obter intervalos mínimo e máximo de prêmios por produtor")
    @GetMapping("/intervalos")
    public ResultadoIntervaloPremiacao obterIntervalos() {
        return service.calcularIntervalos();
    }
}
