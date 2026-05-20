package com.manager.service;

import com.manager.model.Tarefa;
import com.manager.model.Projeto;
import com.manager.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTarefa {
    private List<Tarefa> tarefas;
    
    public GerenciadorTarefa() {
        this.tarefas = new ArrayList<>();
    }
    
    public void criarTarefa(Tarefa tarefa) {
        tarefas.add(tarefa);
        tarefa.getProjeto().adicionarTarefa(tarefa);
        System.out.println("Tarefa criada com sucesso! ID: " + tarefa.getId());
    }
    
    public List<Tarefa> listarTarefas() {
        return new ArrayList<>(tarefas);
    }
    
    public List<Tarefa> listarPorResponsavel(Usuario responsavel) {
        return tarefas.stream().filter(t -> t.getResponsavel() != null && 
                   t.getResponsavel().getId().equals(responsavel.getId())).toList();
    }
    
    public List<Tarefa> listarPorProjeto(Projeto projeto) {
        return tarefas.stream().filter(t -> t.getProjeto().getId().equals(projeto.getId())).toList();
    }
    
    public void atualizarStatus(Long idTarefa, String novoStatus) {
        Tarefa tarefa = buscarPorId(idTarefa);
        if (tarefa != null) {
            tarefa.setStatus(novoStatus);
            System.out.println("Status da tarefa atualizado para: " + novoStatus);
        } else {
            System.out.println("Tarefa não encontrada!");
        }
    }
    
    public Tarefa buscarPorId(Long id) {
        return tarefas.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
    }
    
    public void gerarRelatorioCargaTrabalho() {
        System.out.println("\n========== RELATÓRIO DE CARGA DE TRABALHO ==========");
        System.out.println("Total de tarefas: " + tarefas.size());
        
        System.out.println("\n--- Tarefas por Status ---");
        System.out.println("Pendentes: " + contarPorStatus("Pendente"));
        System.out.println("Em Andamento: " + contarPorStatus("Em Andamento"));
        System.out.println("Concluídas: " + contarPorStatus("Concluído"));
        System.out.println("Canceladas: " + contarPorStatus("Cancelado"));
        
        System.out.println("\n--- Tarefas Atrasadas ---");
        for (Tarefa tarefa : tarefas) {
            if (tarefa.isAtrasada()) {
                System.out.println("⚠️ Tarefa: " + tarefa.getTitulo() + 
                                   " | Projeto: " + tarefa.getProjeto().getNome() +
                                   " | Prazo: " + tarefa.getPrazo());
            }
        }
        System.out.println("===================================================\n");
    }
    
    private long contarPorStatus(String status) {
        return tarefas.stream().filter(t -> t.getStatus().equals(status)).count();
    }
}