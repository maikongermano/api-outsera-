package com.outsera.api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoIntervaloPremiacao {
	
	@JsonProperty("min")
	private List<IntervaloPremiacaoProdutor> intervalosMinimos;
	
	@JsonProperty("max")
	private List<IntervaloPremiacaoProdutor> intervalosMaximos;

}
