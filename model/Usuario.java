package com.manager.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {
    private static long contadorId = 1;
    
    private Long id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private String cargo;
    private String login;
    private String senha;
    private Perfil perfil;
    private LocalDateTime dataCadastro;
    
    public Usuario(String nomeCompleto, String cpf, String email, String cargo, 
                   String login, String senha, Perfil perfil) {
        this.id = contadorId++;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
        this.dataCadastro = LocalDateTime.now();
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Perfil getPerfil() { return perfil; }
    public void setPerfil(Perfil perfil) { this.perfil = perfil; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    
    @Override
    public String toString() {
        return "ID: " + id + " | " + nomeCompleto + " | " + cpf + " | " + email + 
               " | " + cargo + " | " + perfil.getDescricao();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(cpf, usuario.cpf);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}