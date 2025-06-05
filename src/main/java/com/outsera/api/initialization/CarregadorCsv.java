package com.outsera.api.initialization;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.outsera.api.model.Filme;
import com.outsera.api.repository.FilmeRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CarregadorCsv {

    private final FilmeRepository repository;

    @PostConstruct
    public void carregarFilmesCsv() {
        InputStream input = getClass().getResourceAsStream("/movielist.csv");

        if (input == null) {
            throw new RuntimeException("Arquivo movielist.csv não encontrado em resources.");
        }

        boolean carregado = tentarCarregarCsv(input, ',');

        if (!carregado) {
            input = getClass().getResourceAsStream("/movielist.csv");
            if (!tentarCarregarCsv(input, ';')) {
                throw new RuntimeException("Erro ao carregar CSV com ambos os delimitadores.");
            }
        }
    }

    private boolean tentarCarregarCsv(InputStream input, char separador) {
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(input))
                .withCSVParser(new CSVParserBuilder().withSeparator(separador).build())
                .build()) {

            List<String[]> linhas = reader.readAll();
            linhas.remove(0);

            for (String[] linha : linhas) {
                if (linha.length < 4) continue;

                Filme filme = Filme.builder()
                        .ano(Integer.parseInt(linha[0].trim()))
                        .titulo(linha[1].trim())
                        .estudio(linha[2].trim())
                        .produtores(linha[3].trim())
                        .vencedor(linha.length > 4 && "yes".equalsIgnoreCase(linha[4].trim()))
                        .build();

                repository.save(filme);
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
