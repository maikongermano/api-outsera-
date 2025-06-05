package com.outsera.api.repository;

import com.outsera.api.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByVencedorTrue();
}
