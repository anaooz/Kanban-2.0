package br.com.fiap.quadro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.quadro.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
