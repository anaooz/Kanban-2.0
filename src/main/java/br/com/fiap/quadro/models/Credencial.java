package br.com.fiap.quadro.models;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record Credencial(String login, String senha) {
    public Authentication toAuthentication() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
