package com.outsera.api.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.outsera.api.model.Filme;
import com.outsera.api.model.IntervaloPremiacaoProdutor;
import com.outsera.api.model.ResultadoIntervaloPremiacao;
import com.outsera.api.repository.FilmeRepository;

@ExtendWith(MockitoExtension.class)
class IntervaloPremiacaoServiceTest {

    @Mock
    private FilmeRepository filmeRepository;

    @InjectMocks
    private IntervaloPremiacaoService intervaloPremiacaoService;

    private List<Filme> filmesMock;

    @BeforeEach
    void setUp() {
        filmesMock = List.of(
                Filme.builder().ano(2000).titulo("Filme 1").estudio("Estudio A").produtores("Producer A").vencedor(true).build(),
                Filme.builder().ano(2002).titulo("Filme 2").estudio("Estudio A").produtores("Producer A").vencedor(true).build(),
                Filme.builder().ano(2010).titulo("Filme 3").estudio("Estudio B").produtores("Producer B").vencedor(true).build(),
                Filme.builder().ano(2020).titulo("Filme 4").estudio("Estudio B").produtores("Producer B").vencedor(true).build(),
                Filme.builder().ano(2015).titulo("Filme 5").estudio("Estudio C").produtores("Producer C").vencedor(true).build()
        );
    }

    @Test
    void deveCalcularIntervalosCorretamente() {
        when(filmeRepository.findByVencedorTrue()).thenReturn(filmesMock);

        ResultadoIntervaloPremiacao resultado = intervaloPremiacaoService.calcularIntervalos();

        assertThat(resultado).isNotNull();
        assertThat(resultado.getIntervalosMinimos()).isNotEmpty();
        assertThat(resultado.getIntervalosMaximos()).isNotEmpty();

        IntervaloPremiacaoProdutor min = resultado.getIntervalosMinimos().get(0);
        IntervaloPremiacaoProdutor max = resultado.getIntervalosMaximos().get(0);

        assertThat(min.getProdutor()).isIn("Producer A", "Producer B");
        assertThat(min.getIntervalo()).isEqualTo(2);

        assertThat(max.getProdutor()).isIn("Producer A", "Producer B");
        assertThat(max.getIntervalo()).isEqualTo(10);
    }
}
