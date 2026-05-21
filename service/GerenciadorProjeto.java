package com.manager.service;

import com.manager.model.Projeto;
import com.manager.model.Usuario;
import com.manager.model.Equipe;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorProjeto {
    private List<Projeto> projetos;
    
    public GerenciadorProjeto() {
        this.projetos = new ArrayList<>();
    }
    
    public void criarProjeto(Projeto projeto) {
        projetos.add(projeto);
        System.out.println("Projeto criado com sucesso! ID: " + projeto.getId());
    }
    
    public List<Projeto> listarProjetos() {
        return new ArrayList<>(projetos);
    }
    
    public Projeto buscarPorId(Long id) {
        return projetos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }
    
    public List<Projeto> listarPorGerente(Usuario gerente) {
        return projetos.stream().filter(p -> p.getGerenteResponsavel() != null && 
                   p.getGerenteResponsavel().getId().equals(gerente.getId())).collect(Collectors.toList());
    }
    
    public List<Projeto> listarPorStatus(String status) {
        return projetos.stream().filter(p -> p.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
    }
    
    public void atualizarStatus(Long idProjeto, String novoStatus) {
        Projeto projeto = buscarPorId(idProjeto);
        if (projeto != null) {
            projeto.setStatus(novoStatus);
            System.out.println("Status do projeto atualizado para: " + novoStatus);
        } else {
            System.out.println("Projeto não encontrado!");
        }
    }
    
    public void alocarEquipe(Long idProjeto, Equipe equipe) {
        Projeto projeto = buscarPorId(idProjeto);
        if (projeto != null) {
            projeto.adicionarEquipe(equipe);
            equipe.adicionarProjeto(projeto);
            System.out.println("Equipe " + equipe.getNome() + " alocada ao projeto " + projeto.getNome());
        } else {
            System.out.println("Projeto não encontrado!");
        }
    }
    
    public void gerarRelatorioDesempenho() {
        System.out.println("\n========== RELATÓRIO DE DESEMPENHO ==========");
        System.out.println("Total de projetos: " + projetos.size());
        System.out.println("Projetos Planejados: " + listarPorStatus("Planejado").size());
        System.out.println("Projetos em Andamento: " + listarPorStatus("Em Andamento").size());
        System.out.println("Projetos Concluídos: " + listarPorStatus("Concluído").size());
        System.out.println("Projetos Cancelados: " + listarPorStatus("Cancelado").size());
        
        System.out.println("\n--- Projetos Atrasados ---");
        for (Projeto projeto : projetos) {
            if (projeto.getStatus().equals("Em Andamento") && 
                projeto.getDataTerminoPrevista().isBefore(LocalDate.now())) {
                System.out.println("⚠️ " + projeto.getNome() + " - Atrasado desde: " + projeto.getDataTerminoPrevista());
            }
        }
        System.out.println("============================================\n");
    }
}