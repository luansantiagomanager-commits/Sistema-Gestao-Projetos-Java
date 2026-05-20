package com.manager.service;

import com.manager.model.Equipe;
import com.manager.model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorEquipe {
    private List<Equipe> equipes;
    
    public GerenciadorEquipe() {
        this.equipes = new ArrayList<>();
    }
    
    public void criarEquipe(Equipe equipe) {
        equipes.add(equipe);
        System.out.println("Equipe criada com sucesso! ID: " + equipe.getId());
    }
    
    public List<Equipe> listarEquipes() {
        return new ArrayList<>(equipes);
    }
    
    public Equipe buscarPorId(Long id) {
        return equipes.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }
    
    public void adicionarMembro(Long idEquipe, Usuario usuario) {
        Equipe equipe = buscarPorId(idEquipe);
        if (equipe != null) {
            equipe.adicionarMembro(usuario);
            System.out.println("Usuário " + usuario.getNomeCompleto() + " adicionado à equipe " + equipe.getNome());
        } else {
            System.out.println("Equipe não encontrada!");
        }
    }
    
    public void removerMembro(Long idEquipe, Usuario usuario) {
        Equipe equipe = buscarPorId(idEquipe);
        if (equipe != null) {
            equipe.removerMembro(usuario);
            System.out.println("Usuário removido da equipe");
        } else {
            System.out.println("Equipe não encontrada!");
        }
    }
}