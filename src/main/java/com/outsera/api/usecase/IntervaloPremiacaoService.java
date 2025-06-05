package com.outsera.api.usecase;

import com.outsera.api.model.Filme;
import com.outsera.api.model.IntervaloPremiacaoProdutor;
import com.outsera.api.model.ResultadoIntervaloPremiacao;
import com.outsera.api.repository.FilmeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class IntervaloPremiacaoService {

    private final FilmeRepository repository;

    public ResultadoIntervaloPremiacao calcularIntervalos() {
        List<Filme> vencedores = repository.findByVencedorTrue();
        Map<String, List<Integer>> produtorVitorias = new HashMap<>();

        for (Filme filme : vencedores) {
            for (String produtor : filme.getProdutores().split("(,| and )")) {
                produtorVitorias
                        .computeIfAbsent(produtor.trim(), k -> new ArrayList<>())
                        .add(filme.getAno());
            }
        }

        List<IntervaloPremiacaoProdutor> intervalos = new ArrayList<>();

        for (var entry : produtorVitorias.entrySet()) {
            var anos = entry.getValue();
            if (anos.size() < 2) continue;
            Collections.sort(anos);
            for (int i = 1; i < anos.size(); i++) {
                intervalos.add(new IntervaloPremiacaoProdutor(entry.getKey(),
                        anos.get(i) - anos.get(i - 1), anos.get(i - 1), anos.get(i)));
            }
        }

        int min = intervalos.stream().mapToInt(IntervaloPremiacaoProdutor::getIntervalo).min().orElse(0);
        int max = intervalos.stream().mapToInt(IntervaloPremiacaoProdutor::getIntervalo).max().orElse(0);

        return new ResultadoIntervaloPremiacao(
                intervalos.stream().filter(i -> i.getIntervalo() == min).toList(),
                intervalos.stream().filter(i -> i.getIntervalo() == max).toList()
        );
    }
}
