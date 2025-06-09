package com.outsera.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IntervaloPremiacaoProdutor {
	
	@JsonProperty("producer")
    private String produtor;
	
	@JsonProperty("interval")
    private int intervalo;
	
	@JsonProperty("previousWin")
    private int anoAnterior;
	
	@JsonProperty("followingWin")
    private int anoSeguinte;

}
