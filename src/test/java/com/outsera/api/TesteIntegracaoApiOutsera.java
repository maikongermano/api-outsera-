package com.outsera.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.outsera.api.model.IntervaloPremiacaoProdutor;
import com.outsera.api.model.ResultadoIntervaloPremiacao;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TesteIntegracaoApiOutsera {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void deveRetornarIntervalosDePremiacaoCorretamente() {
        String url = "http://localhost:" + port + "/produtores/intervalos";

        ResponseEntity<ResultadoIntervaloPremiacao> response = restTemplate.getForEntity(url, ResultadoIntervaloPremiacao.class);

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();

        ResultadoIntervaloPremiacao body = response.getBody();

        assertThat(body).isNotNull();

        assertThat(body.getIntervalosMinimos()).isNotNull().isNotEmpty();
        assertThat(body.getIntervalosMaximos()).isNotNull().isNotEmpty();

        IntervaloPremiacaoProdutor minProdutor = body.getIntervalosMinimos().get(0);
        IntervaloPremiacaoProdutor maxProdutor = body.getIntervalosMaximos().get(0);

        assertThat(minProdutor.getProdutor()).isNotNull();
        assertThat(minProdutor.getIntervalo()).isGreaterThanOrEqualTo(0);
        assertThat(minProdutor.getAnoAnterior()).isGreaterThan(1900);
        assertThat(minProdutor.getAnoSeguinte()).isGreaterThan(minProdutor.getAnoAnterior());

        assertThat(maxProdutor.getProdutor()).isNotNull();
        assertThat(maxProdutor.getIntervalo()).isGreaterThanOrEqualTo(0);
        assertThat(maxProdutor.getAnoAnterior()).isGreaterThan(1900);
        assertThat(maxProdutor.getAnoSeguinte()).isGreaterThan(maxProdutor.getAnoAnterior());
    }
}
