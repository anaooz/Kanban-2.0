package br.com.fiap.quadro.controllers;

import java.util.*;

import org.slf4j.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.quadro.models.Usuario;

@RestController
public class UsuarioController {

    Logger log = LoggerFactory.getLogger(UsuarioController.class);

    List<Usuario> usuarios = new ArrayList<>();

    @GetMapping("/api/usuario")
    public List<Usuario> index(){
        return usuarios;
    }

    @PostMapping("/api/usuario")
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
        log.info("cadastrando usuário {}", usuario);
        usuario.setId(usuarios.size() + 1l);
        usuarios.add(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("api/usuario/{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id){
        log.info("detalhando usuário {}", id);
        var usuarioEncontrado = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();

        if(usuarioEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(usuarioEncontrado.get());
    }

    @PutMapping("/api/usuario/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario){
        log.info("atualizando usuário {}", id);
        var usuarioEncontrado = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();

        if(usuarioEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarios.remove(usuarioEncontrado.get());
        usuario.setId(id);
        usuarios.add(usuario);

        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("api/usuario/{id}")
    public ResponseEntity<Usuario> destroy(@PathVariable Long id){
        log.info("deletando usuário {}", id);
        var usuarioEncontrado = usuarios.stream().filter(u -> u.getId().equals(id)).findFirst();

        if(usuarioEncontrado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        usuarios.remove(usuarioEncontrado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
