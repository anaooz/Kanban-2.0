package br.com.fiap.quadro.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.quadro.models.Usuario;
import br.com.fiap.quadro.repository.UsuarioRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    UsuarioRepository usuarioRepository;

    public void run(String... args) throws Exception {
        usuarioRepository.saveAll(List.of(
            new Usuario(1L, "mateus@email.com", "fiap123", true),
            new Usuario(2L, "amanda@email.com", "fiap123", false),
            new Usuario(3L, "joao@email.com", "123fiap", true)
        ));
    }
}
