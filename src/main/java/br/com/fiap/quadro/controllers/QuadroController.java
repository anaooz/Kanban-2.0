package br.com.fiap.quadro.controllers;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.quadro.models.Quadro;
import br.com.fiap.quadro.repository.QuadroRepository;

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
    public ResponseEntity<Quadro> create(@RequestBody Quadro quadro){
        log.info("cadastrando quadro {}", quadro);
        repository.save(quadro);
        return ResponseEntity.status(HttpStatus.CREATED).body(quadro);
    }

    @GetMapping("{id}")
    public ResponseEntity<Quadro> show(@PathVariable Long id){
        log.info("detalhando quadro {}", id);
        var quadroEspecificado = repository.findById(id);

        if(quadroEspecificado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(quadroEspecificado.get());
    }

    @PutMapping("{id}")
    public ResponseEntity<Quadro> update(@PathVariable Long id, @RequestBody Quadro quadro){
        log.info("atualizando quadro {}", id);
        var quadroEspecificado = repository.findById(id);

        if(quadroEspecificado.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        quadro.setId(id);
        repository.save(quadro);

        return ResponseEntity.ok(quadro);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Quadro> destroy(@PathVariable Long id){
        log.info("deletando quadro {}", id);
        var quadroEspecificado = repository.findById(id);

        if(quadroEspecificado.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        repository.delete(quadroEspecificado.get());

        return ResponseEntity.noContent().build();
    }
}
