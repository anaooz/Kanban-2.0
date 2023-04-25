package br.com.fiap.quadro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.quadro.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long>{
    Optional<Conta> findByEmail(String login);
}
