package br.com.fiap.quadro.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.quadro.models.Conta;
import br.com.fiap.quadro.models.Credencial;
import br.com.fiap.quadro.models.JwtToken;
import br.com.fiap.quadro.repository.ContaRepository;

@Service
public class TokenJwtService {

    @Value("${jwt.secret}")
    String secret;

    @Autowired
    ContaRepository repository;

    public JwtToken generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);

        var token = JWT.create()
                    .withExpiresAt(Instant.now().plus(2, ChronoUnit.HOURS))
                    .withSubject(credencial.login())
                    .withIssuer("Quadro")
                    .sign(alg);

        return new JwtToken(token);
    }

    public Conta validate(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var login = JWT.require(alg)
                    .withIssuer("Quadro")
                    .build()
                    .verify(token)
                    .getSubject();
        
        return repository.findByEmail(login).orElseThrow(() -> new RuntimeException("Token inválido"));
    }
}
