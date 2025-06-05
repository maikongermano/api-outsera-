package com.outsera.api.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoIntervaloPremiacao {
    private List<IntervaloPremiacaoProdutor> intervalosMinimos;
    private List<IntervaloPremiacaoProdutor> intervalosMaximos;
}
