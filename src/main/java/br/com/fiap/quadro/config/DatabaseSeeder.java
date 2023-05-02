package br.com.fiap.quadro.config;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.quadro.models.Conta;
import br.com.fiap.quadro.models.Quadro;
import br.com.fiap.quadro.models.Usuario;
import br.com.fiap.quadro.repository.ContaRepository;
import br.com.fiap.quadro.repository.QuadroRepository;
import br.com.fiap.quadro.repository.UsuarioRepository;
@Configuration
public class DatabaseSeeder implements CommandLineRunner{

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    QuadroRepository quadroRepository;

    @Autowired
    ContaRepository contaRepository;

    @Override
    public void run(String... args) throws Exception {
        Usuario u1 = new Usuario(1L, "mateus@fiap.com", true);
        Usuario u2 = new Usuario(2L, "amanda@fiap.com", true);
        Usuario u3 = new Usuario(3L, "joao@fiap.com", false);
    
        usuarioRepository.saveAll(List.of(u1, u2, u3));

        quadroRepository.saveAll(List.of(
            Quadro.builder().titulo("exemplo 1").cor("vermelho").data(LocalDate.now()).usuario(u1).build(),
            Quadro.builder().titulo("exemplo 2").cor("azul").data(LocalDate.now()).usuario(u3).build(),
            Quadro.builder().titulo("exemplo 3").cor("verde").data(LocalDate.now()).usuario(u2).build(),
            Quadro.builder().titulo("exemplo 50").cor("rosa").data(LocalDate.now()).usuario(u3).build(),
            Quadro.builder().titulo("exemplo 99").cor("branco").data(LocalDate.now()).usuario(u1).build(),
            Quadro.builder().titulo("exemplo 1000").cor("roxo").data(LocalDate.now()).usuario(u3).build()
            ));
        
        contaRepository.save(Conta.builder()
        .nome("Mateus")
        .email("mateus@fiap.com.br")
        .senha("170703")
        .build()
        );
    }
}
