package com.manager.model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
    private static long contadorId = 1;
    
    private Long id;
    private String nome;
    private String descricao;
    private List<Usuario> membros;
    private List<Projeto> projetosAtuantes;
    
    public Equipe(String nome, String descricao) {
        this.id = contadorId++;
        this.nome = nome;
        this.descricao = descricao;
        this.membros = new ArrayList<>();
        this.projetosAtuantes = new ArrayList<>();
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public List<Usuario> getMembros() { return membros; }
    public List<Projeto> getProjetosAtuantes() { return projetosAtuantes; }
    
    public void adicionarMembro(Usuario usuario) {
        if (!membros.contains(usuario)) {
            membros.add(usuario);
        }
    }
    
    public void removerMembro(Usuario usuario) {
        membros.remove(usuario);
    }
    
    public void adicionarProjeto(Projeto projeto) {
        if (!projetosAtuantes.contains(projeto)) {
            projetosAtuantes.add(projeto);
        }
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | " + nome + " | Membros: " + membros.size() + 
               " | Projetos: " + projetosAtuantes.size();
    }
}