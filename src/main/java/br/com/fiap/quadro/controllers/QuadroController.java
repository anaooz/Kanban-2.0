package br.com.fiap.quadro.controllers;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.hateoas.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.quadro.exceptions.RestNotFoundException;
import br.com.fiap.quadro.models.Quadro;
import br.com.fiap.quadro.repository.QuadroRepository;
import br.com.fiap.quadro.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/quadro")
@Slf4j
@SecurityRequirement(name = "bearer-key")
@Tag(name = "quadro")
public class QuadroController {

    @Autowired
    QuadroRepository quadroRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PagedResourcesAssembler<Object> assembler;

    @GetMapping
    public PagedModel<EntityModel<Object>> index(@RequestParam(required = false) String busca, @ParameterObject @PageableDefault(size = 8) Pageable pageable){
        Page<Quadro> page =  (busca == null) ? 
        quadroRepository.findAll(pageable) :
        quadroRepository.findByTitulo(busca, pageable);

        return assembler.toModel(page.map(Quadro::toModel));
    }

    @PostMapping
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Quadro cadastrado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Campos não preenchidos")
    })
    public ResponseEntity<EntityModel<Quadro>> create(@RequestBody @Valid Quadro quadro){
        log.info("cadastrando quadro {}", quadro);
        quadroRepository.save(quadro);

        return ResponseEntity
                .created(quadro.toModel().getRequiredLink("self").toUri())
                .body(quadro.toModel());
    }

    @GetMapping("{id}")
    @Operation(summary = "Procurar quadro",
                description = "Endpoint que recebe um id e retorna um Quadro específico.")
    public EntityModel<Quadro> show(@PathVariable Long id){
        log.info("detalhando quadro {}", id);
        getQuadro(id);

        return getQuadro(id).toModel();
    }

    @PutMapping("{id}")
    public EntityModel<Quadro> update(@PathVariable Long id, @RequestBody @Valid Quadro quadro){
        log.info("atualizando quadro {}", id);
        getQuadro(id);

        quadro.setId(id);
        quadroRepository.save(quadro);

        return quadro.toModel();
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
