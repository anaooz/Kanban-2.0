package br.com.fiap.quadro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.quadro.exceptions.RestNotFoundException;
import br.com.fiap.quadro.models.Quadro;
import br.com.fiap.quadro.repository.QuadroRepository;
import br.com.fiap.quadro.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/quadro")
@Slf4j
public class QuadroController {

    @Autowired
    QuadroRepository quadroRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public Page<Quadro> index(@RequestParam(required = false) String busca, @PageableDefault(size = 8) Pageable pageable){
        if(busca == null) return quadroRepository.findAll(pageable);

        return quadroRepository.findByTitulo(busca, pageable);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Quadro quadro){
        log.info("cadastrando quadro {}", quadro);
        quadroRepository.save(quadro);
        quadro.setUsuario(usuarioRepository.findById(quadro.getUsuario().getId()).get());

        return ResponseEntity.status(HttpStatus.CREATED).body(quadro);
    }

    @GetMapping("{id}")
    public ResponseEntity<Quadro> show(@PathVariable Long id){
        log.info("detalhando quadro {}", id);
        getQuadro(id);

        return ResponseEntity.ok(getQuadro(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Quadro> update(@PathVariable Long id, @RequestBody @Valid Quadro quadro){
        log.info("atualizando quadro {}", id);
        getQuadro(id);

        quadro.setId(id);
        quadroRepository.save(quadro);

        return ResponseEntity.ok(quadro);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Quadro> destroy(@PathVariable Long id){
        log.info("deletando quadro {}", id);
        var quadro = quadroRepository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Erro ao apagar, quadro não encontrado"));

        quadroRepository.delete(quadro);

        return ResponseEntity.noContent().build();
    }

    private Quadro getQuadro(Long id){
        return quadroRepository.findById(id)
        .orElseThrow(() -> new RestNotFoundException("Quadro não encontrado"));
    }
}
