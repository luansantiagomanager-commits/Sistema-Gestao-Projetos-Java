package com.manager.model;

import java.time.LocalDate;

public class Tarefa {
    private static long contadorId = 1;
    
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private String status; // Pendente, Em Andamento, Concluído, Cancelado
    private Usuario responsavel;
    private Projeto projeto;
    
    public Tarefa(String titulo, String descricao, LocalDate prazo, 
                  Usuario responsavel, Projeto projeto) {
        this.id = contadorId++;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.status = "Pendente";
        this.responsavel = responsavel;
        this.projeto = projeto;
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getPrazo() { return prazo; }
    public void setPrazo(LocalDate prazo) { this.prazo = prazo; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Usuario getResponsavel() { return responsavel; }
    public void setResponsavel(Usuario responsavel) { this.responsavel = responsavel; }
    public Projeto getProjeto() { return projeto; }
    
    public boolean isAtrasada() {
        return status.equals("Pendente") && prazo.isBefore(LocalDate.now());
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | " + titulo + " | Status: " + status + 
               " | Prazo: " + prazo + " | Responsável: " + 
               (responsavel != null ? responsavel.getNomeCompleto() : "Não atribuído");
    }
}