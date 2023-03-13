package br.com.fiap.quadro.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.quadro.models.Usuario;

@RestController
public class UsuarioController {
    @RequestMapping("/api/usuario")
    public Usuario show(){
        
        return new Usuario("amanda@fiap.com", "12345");
    }
}
