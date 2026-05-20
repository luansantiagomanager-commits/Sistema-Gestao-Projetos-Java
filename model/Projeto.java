package com.manager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private static long contadorId = 1;
    
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTerminoPrevista;
    private String status; // Planejado, Em Andamento, Concluído, Cancelado
    private Usuario gerenteResponsavel;
    private List<Equipe> equipesAlocadas;
    private List<Tarefa> tarefas;
    
    public Projeto(String nome, String descricao, LocalDate dataInicio, 
                   LocalDate dataTerminoPrevista, Usuario gerenteResponsavel) {
        this.id = contadorId++;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTerminoPrevista = dataTerminoPrevista;
        this.status = "Planejado";
        this.gerenteResponsavel = gerenteResponsavel;
        this.equipesAlocadas = new ArrayList<>();
        this.tarefas = new ArrayList<>();
    }
    
    // Getters e Setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataTerminoPrevista() { return dataTerminoPrevista; }
    public void setDataTerminoPrevista(LocalDate dataTerminoPrevista) { this.dataTerminoPrevista = dataTerminoPrevista; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Usuario getGerenteResponsavel() { return gerenteResponsavel; }
    public void setGerenteResponsavel(Usuario gerenteResponsavel) { this.gerenteResponsavel = gerenteResponsavel; }
    public List<Equipe> getEquipesAlocadas() { return equipesAlocadas; }
    public List<Tarefa> getTarefas() { return tarefas; }
    
    public void adicionarEquipe(Equipe equipe) {
        if (!equipesAlocadas.contains(equipe)) {
            equipesAlocadas.add(equipe);
        }
    }
    
    public void adicionarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
    }
    
    @Override
    public String toString() {
        return "ID: " + id + " | " + nome + " | " + status + " | Início: " + dataInicio + 
               " | Término previsto: " + dataTerminoPrevista + " | Gerente: " + 
               (gerenteResponsavel != null ? gerenteResponsavel.getNomeCompleto() : "Nenhum");
    }
}