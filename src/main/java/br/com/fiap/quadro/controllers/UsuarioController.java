package br.com.fiap.quadro.controllers;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.quadro.exceptions.RestNotFoundException;
import br.com.fiap.quadro.models.Usuario;
import br.com.fiap.quadro.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    UsuarioRepository repository;

    @GetMapping
    public List<Usuario> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        log.info("cadastrando usuário {}", usuario);
        repository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id){
        log.info("detalhando usuário {}", id);

        return ResponseEntity.ok(getUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
        log.info("atualizando usuário {}", id);
        getUsuario(id);
        usuario.setId(id);
        repository.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        log.info("deletando usuário {}", id);
        var conta = getUsuario(id);
        conta.setAtiva(false);
        repository.save(conta);

        return ResponseEntity.noContent().build();
    }

    private Usuario getUsuario(Long id){
        return repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Usuário não encontrado"));
    }
}
