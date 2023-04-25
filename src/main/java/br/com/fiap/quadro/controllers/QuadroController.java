package br.com.fiap.quadro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
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

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @PageableDefault(size = 8) Pageable pageable){
        Page<Quadro> page =  (busca == null) ? 
        quadroRepository.findAll(pageable) :
        quadroRepository.findByTitulo(busca, pageable);

        return assembler.toModel(page.map(Quadro::toModel));
    }

    @PostMapping
    public ResponseEntity<EntityModel<Quadro>> create(@RequestBody @Valid Quadro quadro){
        log.info("cadastrando quadro {}", quadro);
        quadroRepository.save(quadro);

        return ResponseEntity
                .created(quadro.toModel().getRequiredLink("self").toUri())
                .body(quadro.toModel());
    }

    @GetMapping("{id}")
    public EntityModel<Quadro> show(@PathVariable Long id){
        log.info("detalhando quadro {}", id);
        getQuadro(id);

        return getQuadro(id).toModel();
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
