package br.com.fiap.quadro.repository;

import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.quadro.models.Quadro;

public interface QuadroRepository extends JpaRepository<Quadro, Long>{
    Page<Quadro> findByTitulo(String busca, Pageable pageable);
}
