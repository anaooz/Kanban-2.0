package br.com.fiap.quadro.controllers;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.quadro.models.Usuario;
import br.com.fiap.quadro.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(UsuarioController.class);

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
        var usuarioEncontrado = repository.findById(id);

        if(usuarioEncontrado.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarioEncontrado.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
        log.info("atualizando usuário {}", id);
        var usuarioEncontrado = repository.findById(id);

        if(usuarioEncontrado.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        usuario.setId(id);
        repository.save(usuario);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        log.info("deletando usuário {}", id);
        var usuarioEncontrado = repository.findById(id);

        if(usuarioEncontrado.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        repository.delete(usuarioEncontrado.get());;

        return ResponseEntity.noContent().build();
    }
}
