package br.com.fiap.quadro.controllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.quadro.models.Quadro;

@RestController
public class QuadroController {
    @RequestMapping("/api/despesas")
    public Quadro show(){
        
        return new Quadro("exemplo 1", "mateus@fiap.com.br", "vermelho", LocalDate.of(2023, 3, 2));
    }
}
//return String.format("{valor: %s, data: '%s', descricao: '%s'}", despesa.getValor(), despesa.getData(), despesa.getDescricao())
