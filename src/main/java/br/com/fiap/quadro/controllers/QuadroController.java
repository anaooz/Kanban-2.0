package br.com.fiap.quadro.controllers;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.quadro.exceptions.RestNotFoundException;
import br.com.fiap.quadro.models.Quadro;
import br.com.fiap.quadro.repository.QuadroRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/quadro")
public class QuadroController {

    Logger log = LoggerFactory.getLogger(QuadroController.class);

    @Autowired
    QuadroRepository repository;

    @GetMapping
    public List<Quadro> index(){
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Quadro quadro){
        log.info("cadastrando quadro {}", quadro);
        repository.save(quadro);
        return ResponseEntity.status(HttpStatus.CREATED).body(quadro);
    }

    @GetMapping("{id}")
    public ResponseEntity<Quadro> show(@PathVariable Long id){
        log.info("detalhando quadro {}", id);
        var quadro = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Quadro não encontrado"));

        return ResponseEntity.ok(quadro);
    }

    @PutMapping("{id}")
    public ResponseEntity<Quadro> update(@PathVariable Long id, @RequestBody @Valid Quadro quadro){
        log.info("atualizando quadro {}", id);
        repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao atualizar, quadro não encontrado"));

        quadro.setId(id);
        repository.save(quadro);

        return ResponseEntity.ok(quadro);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Quadro> destroy(@PathVariable Long id){
        log.info("deletando quadro {}", id);
        var quadro = repository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, quadro não encontrado"));

        repository.delete(quadro);

        return ResponseEntity.noContent().build();
    }
}
