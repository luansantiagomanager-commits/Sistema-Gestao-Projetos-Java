package com.manager.model;

public enum Perfil {
    ADMINISTRADOR("Administrador"),
    GERENTE("Gerente"),
    COLABORADOR("Colaborador");
    
    private String descricao;
    
    Perfil(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
}