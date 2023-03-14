package br.com.fiap.quadro.controllers;

import java.util.*;

import org.slf4j.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.quadro.models.Quadro;

@RestController
public class QuadroController {

    Logger log = LoggerFactory.getLogger(QuadroController.class);

    List<Quadro> quadros = new ArrayList<>();

    @GetMapping("/api/quadro")
    public List<Quadro> index(){
        return quadros;
    }

    @PostMapping("/api/quadro")
    public ResponseEntity<Quadro> create(@RequestBody Quadro quadro){
        log.info("cadastrando quadro {}", quadro);
        quadro.setId(quadros.size() + 1l);
        quadros.add(quadro);
        return ResponseEntity.status(HttpStatus.CREATED).body(quadro);
    }

    @GetMapping("api/quadro/{id}")
    public ResponseEntity<Quadro> show(@PathVariable Long id){
        log.info("detalhando quadro {}", id);
        var quadroEspecificado = quadros.stream().filter(q -> q.getId().equals(id)).findFirst();

        if(quadroEspecificado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(quadroEspecificado.get());
    }

    @PutMapping("api/quadro/{id}")
    public ResponseEntity<Quadro> update(@PathVariable Long id, @RequestBody Quadro quadro){
        log.info("atualizando quadro {}", id);
        var quadroEspecificado = quadros.stream().filter(q -> q.getId().equals(id)).findFirst();

        if(quadroEspecificado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        quadros.remove(quadroEspecificado.get());
        quadro.setId(id);
        quadros.add(quadro);

        return ResponseEntity.ok(quadro);
    }

    @DeleteMapping("api/quadro/{id}")
    public ResponseEntity<Quadro> destroy(@PathVariable Long id){
        log.info("deletando quadro {}", id);
        var quadroEspecificado = quadros.stream().filter(q -> q.getId().equals(id)).findFirst();

        if(quadroEspecificado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        quadros.remove(quadroEspecificado.get());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
