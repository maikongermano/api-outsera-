package com.outsera.api.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntervaloPremiacaoProdutor {
    private String produtor;
    private int intervalo;
    private int anoAnterior;
    private int anoSeguinte;
}
