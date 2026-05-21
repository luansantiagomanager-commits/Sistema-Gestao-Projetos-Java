package com.manager.service;

import com.manager.model.Perfil;
import com.manager.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GerenciadorUsuario {
    private List<Usuario> usuarios;
    private Usuario usuarioLogado;
    
    public GerenciadorUsuario() {
        this.usuarios = new ArrayList<>();
        this.usuarioLogado = null;
        // Criar usuário administrador padrão
        Usuario admin = new Usuario("Admin Sistema", "000.000.000-00", "admin@manager.com", 
                                    "Administrador", "admin", "admin123", Perfil.ADMINISTRADOR);
        usuarios.add(admin);
    }
    
    public boolean cadastrarUsuario(Usuario usuario) {
        if (buscarPorCpf(usuario.getCpf()) != null) {
            System.out.println("Erro: Já existe um usuário com este CPF!");
            return false;
        }
        if (buscarPorLogin(usuario.getLogin()) != null) {
            System.out.println("Erro: Já existe um usuário com este login!");
            return false;
        }
        usuarios.add(usuario);
        System.out.println("Usuário cadastrado com sucesso! ID: " + usuario.getId());
        return true;
    }
    
    public boolean fazerLogin(String login, String senha) {
        Optional<Usuario> usuario = usuarios.stream()
                .filter(u -> u.getLogin().equals(login) && u.getSenha().equals(senha))
                .findFirst();
        
        if (usuario.isPresent()) {
            usuarioLogado = usuario.get();
            System.out.println("Login realizado com sucesso! Bem-vindo, " + usuarioLogado.getNomeCompleto());
            return true;
        } else {
            System.out.println("Erro: Login ou senha incorretos!");
            return false;
        }
    }
    
    public void logout() {
        usuarioLogado = null;
        System.out.println("Logout realizado com sucesso!");
    }
    
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    public boolean isAdmin() {
        return usuarioLogado != null && usuarioLogado.getPerfil() == Perfil.ADMINISTRADOR;
    }
    
    public boolean isGerente() {
        return usuarioLogado != null && usuarioLogado.getPerfil() == Perfil.GERENTE;
    }
    
    public List<Usuario> listarUsuarios() {
        return new ArrayList<>(usuarios);
    }
    
    public Usuario buscarPorId(Long id) {
        return usuarios.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
    }
    
    public Usuario buscarPorCpf(String cpf) {
        return usuarios.stream().filter(u -> u.getCpf().equals(cpf)).findFirst().orElse(null);
    }
    
    public Usuario buscarPorLogin(String login) {
        return usuarios.stream().filter(u -> u.getLogin().equals(login)).findFirst().orElse(null);
    }
    
    public List<Usuario> listarPorPerfil(Perfil perfil) {
        return usuarios.stream().filter(u -> u.getPerfil() == perfil).collect(Collectors.toList());
    }
}