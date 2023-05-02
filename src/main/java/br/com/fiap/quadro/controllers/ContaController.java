package br.com.fiap.quadro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.quadro.models.Conta;
import br.com.fiap.quadro.models.Credencial;
import br.com.fiap.quadro.repository.ContaRepository;
import br.com.fiap.quadro.service.TokenJwtService;
import jakarta.validation.Valid;

public class ContaController {
    @Autowired
    ContaRepository repository;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    TokenJwtService tokenJwtService;

    @PostMapping("/api/registrar")
    public ResponseEntity<Conta> registrar(@RequestBody @Valid Conta conta){
        conta.setSenha(encoder.encode(conta.getSenha()));
        repository.save(conta);

        return ResponseEntity.status(HttpStatus.CREATED).body(conta);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Credencial credencial){
        manager.authenticate(credencial.toAuthentication());
        var token = tokenJwtService.generateToken(credencial);

        return ResponseEntity.ok(token);
    }
}
