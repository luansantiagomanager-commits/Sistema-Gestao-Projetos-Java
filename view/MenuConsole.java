package com.manager.view;

import com.manager.model.*;
import com.manager.service.*;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuConsole {
    private Scanner scanner;
    private GerenciadorUsuario gerenciadorUsuario;
    private GerenciadorProjeto gerenciadorProjeto;
    private GerenciadorEquipe gerenciadorEquipe;
    private GerenciadorTarefa gerenciadorTarefa;
    
    public MenuConsole() {
        this.scanner = new Scanner(System.in);
        this.gerenciadorUsuario = new GerenciadorUsuario();
        this.gerenciadorProjeto = new GerenciadorProjeto();
        this.gerenciadorEquipe = new GerenciadorEquipe();
        this.gerenciadorTarefa = new GerenciadorTarefa();
    }
    
    public void iniciar() {
        System.out.println("========================================");
        System.out.println("  SISTEMA DE GESTÃO DE PROJETOS v1.0");
        System.out.println("========================================");
        
        boolean executando = true;
        while (executando) {
            if (gerenciadorUsuario.getUsuarioLogado() == null) {
                executando = menuLogin();
            } else {
                executando = menuPrincipal();
            }
        }
        System.out.println("Sistema encerrado!");
    }
    
    private boolean menuLogin() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        if (gerenciadorUsuario.fazerLogin(login, senha)) {
            return true;
        }
        return true;
    }
    
    private boolean menuPrincipal() {
        Usuario usuario = gerenciadorUsuario.getUsuarioLogado();
        System.out.println("\n========================================");
        System.out.println("Bem-vindo, " + usuario.getNomeCompleto());
        System.out.println("Perfil: " + usuario.getPerfil().getDescricao());
        System.out.println("========================================");
        
        if (gerenciadorUsuario.isAdmin()) {
            return menuAdministrador();
        } else if (gerenciadorUsuario.isGerente()) {
            return menuGerente();
        } else {
            return menuColaborador();
        }
    }
    
    private boolean menuAdministrador() {
        System.out.println("\n--- MENU ADMINISTRADOR ---");
        System.out.println("1 - Cadastrar Usuário");
        System.out.println("2 - Listar Usuários");
        System.out.println("3 - Cadastrar Projeto");
        System.out.println("4 - Listar Projetos");
        System.out.println("5 - Cadastrar Equipe");
        System.out.println("6 - Listar Equipes");
        System.out.println("7 - Gerenciar Tarefas");
        System.out.println("8 - Relatórios");
        System.out.println("9 - Logout");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        
        switch (opcao) {
            case 1: cadastrarUsuario(); break;
            case 2: listarUsuarios(); break;
            case 3: cadastrarProjeto(); break;
            case 4: listarProjetos(); break;
            case 5: cadastrarEquipe(); break;
            case 6: listarEquipes(); break;
            case 7: menuGerenciarTarefas(); break;
            case 8: menuRelatorios(); break;
            case 9: gerenciadorUsuario.logout(); break;
            case 0: return false;
            default: System.out.println("Opção inválida!");
        }
        return true;
    }
    
    private boolean menuGerente() {
        System.out.println("\n--- MENU GERENTE ---");
        System.out.println("1 - Meus Projetos");
        System.out.println("2 - Criar Projeto");
        System.out.println("3 - Atualizar Status do Projeto");
        System.out.println("4 - Criar Tarefa");
        System.out.println("5 - Listar Tarefas do Projeto");
        System.out.println("6 - Relatórios");
        System.out.println("7 - Logout");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        
        switch (opcao) {
            case 1: listarMeusProjetos(); break;
            case 2: cadastrarProjeto(); break;
            case 3: atualizarStatusProjeto(); break;
            case 4: criarTarefa(); break;
            case 5: listarTarefasPorProjeto(); break;
            case 6: menuRelatorios(); break;
            case 7: gerenciadorUsuario.logout(); break;
            case 0: return false;
            default: System.out.println("Opção inválida!");
        }
        return true;
    }
    
    private boolean menuColaborador() {
        System.out.println("\n--- MENU COLABORADOR ---");
        System.out.println("1 - Minhas Tarefas");
        System.out.println("2 - Atualizar Status da Tarefa");
        System.out.println("3 - Logout");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        
        switch (opcao) {
            case 1: listarMinhasTarefas(); break;
            case 2: atualizarStatusTarefa(); break;
            case 3: gerenciadorUsuario.logout(); break;
            case 0: return false;
            default: System.out.println("Opção inválida!");
        }
        return true;
    }
    
    private void cadastrarUsuario() {
        System.out.println("\n--- CADASTRO DE USUÁRIO ---");
        System.out.print("Nome completo: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("E-mail: ");
        String email = scanner.nextLine();
        System.out.print("Cargo: ");
        String cargo = scanner.nextLine();
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();
        
        System.out.println("Perfil:");
        System.out.println("1 - Administrador");
        System.out.println("2 - Gerente");
        System.out.println("3 - Colaborador");
        System.out.print("Opção: ");
        int opcaoPerfil = Integer.parseInt(scanner.nextLine());
        
        Perfil perfil;
        switch (opcaoPerfil) {
            case 1: perfil = Perfil.ADMINISTRADOR; break;
            case 2: perfil = Perfil.GERENTE; break;
            default: perfil = Perfil.COLABORADOR;
        }
        
        Usuario usuario = new Usuario(nome, cpf, email, cargo, login, senha, perfil);
        gerenciadorUsuario.cadastrarUsuario(usuario);
    }
    
    private void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUÁRIOS ---");
        for (Usuario u : gerenciadorUsuario.listarUsuarios()) {
            System.out.println(u);
        }
    }
    
    private void cadastrarProjeto() {
        System.out.println("\n--- CADASTRO DE PROJETO ---");
        System.out.print("Nome do projeto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Data de início (AAAA-MM-DD): ");
        LocalDate dataInicio = LocalDate.parse(scanner.nextLine());
        System.out.print("Data de término prevista (AAAA-MM-DD): ");
        LocalDate dataTermino = LocalDate.parse(scanner.nextLine());
        
        System.out.println("Gerente responsável (ID):");
        listarUsuariosGerentes();
        System.out.print("ID do gerente: ");
        Long idGerente = Long.parseLong(scanner.nextLine());
        Usuario gerente = gerenciadorUsuario.buscarPorId(idGerente);
        
        if (gerente != null && gerente.getPerfil() == Perfil.GERENTE) {
            Projeto projeto = new Projeto(nome, descricao, dataInicio, dataTermino, gerente);
            gerenciadorProjeto.criarProjeto(projeto);
        } else {
            System.out.println("Gerente inválido!");
        }
    }
    
    private void listarUsuariosGerentes() {
        for (Usuario u : gerenciadorUsuario.listarPorPerfil(Perfil.GERENTE)) {
            System.out.println(u);
        }
    }
    
    private void listarProjetos() {
        System.out.println("\n--- LISTA DE PROJETOS ---");
        for (Projeto p : gerenciadorProjeto.listarProjetos()) {
            System.out.println(p);
        }
    }
    
    private void listarMeusProjetos() {
        Usuario usuario = gerenciadorUsuario.getUsuarioLogado();
        System.out.println("\n--- MEUS PROJETOS ---");
        for (Projeto p : gerenciadorProjeto.listarPorGerente(usuario)) {
            System.out.println(p);
        }
    }
    
    private void cadastrarEquipe() {
        System.out.println("\n--- CADASTRO DE EQUIPE ---");
        System.out.print("Nome da equipe: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        
        Equipe equipe = new Equipe(nome, descricao);
        gerenciadorEquipe.criarEquipe(equipe);
        
        System.out.print("Deseja adicionar membros agora? (S/N): ");
        if (scanner.nextLine().equalsIgnoreCase("S")) {
            adicionarMembrosEquipe(equipe.getId());
        }
    }
    
    private void adicionarMembrosEquipe(Long idEquipe) {
        System.out.println("\n--- ADICIONAR MEMBROS ---");
        listarUsuarios();
        System.out.print("ID do usuário (0 para sair): ");
        Long id = Long.parseLong(scanner.nextLine());
        
        while (id != 0) {
            Usuario usuario = gerenciadorUsuario.buscarPorId(id);
            if (usuario != null) {
                gerenciadorEquipe.adicionarMembro(idEquipe, usuario);
            } else {
                System.out.println("Usuário não encontrado!");
            }
            System.out.print("Próximo ID (0 para sair): ");
            id = Long.parseLong(scanner.nextLine());
        }
    }
    
    private void listarEquipes() {
        System.out.println("\n--- LISTA DE EQUIPES ---");
        for (Equipe e : gerenciadorEquipe.listarEquipes()) {
            System.out.println(e);
        }
    }
    
    private void menuGerenciarTarefas() {
        System.out.println("\n--- GERENCIAR TAREFAS ---");
        System.out.println("1 - Criar Tarefa");
        System.out.println("2 - Listar todas as Tarefas");
        System.out.println("3 - Atualizar Status da Tarefa");
        System.out.print("Opção: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        
        switch (opcao) {
            case 1: criarTarefa(); break;
            case 2: listarTodasTarefas(); break;
            case 3: atualizarStatusTarefa(); break;
            default: System.out.println("Opção inválida!");
        }
    }
    
    private void criarTarefa() {
        System.out.println("\n--- CRIAR TAREFA ---");
        
        listarProjetos();
        System.out.print("ID do projeto: ");
        Long idProjeto = Long.parseLong(scanner.nextLine());
        Projeto projeto = gerenciadorProjeto.buscarPorId(idProjeto);
        
        if (projeto == null) {
            System.out.println("Projeto não encontrado!");
            return;
        }
        
        System.out.print("Título da tarefa: ");
        String titulo = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Prazo (AAAA-MM-DD): ");
        LocalDate prazo = LocalDate.parse(scanner.nextLine());
        
        System.out.println("Responsável (ID do colaborador):");
        for (Usuario u : gerenciadorUsuario.listarPorPerfil(Perfil.COLABORADOR)) {
            System.out.println(u);
        }
        System.out.print("ID do responsável: ");
        Long idResponsavel = Long.parseLong(scanner.nextLine());
        Usuario responsavel = gerenciadorUsuario.buscarPorId(idResponsavel);
        
        if (responsavel != null) {
            Tarefa tarefa = new Tarefa(titulo, descricao, prazo, responsavel, projeto);
            gerenciadorTarefa.criarTarefa(tarefa);
        } else {
            System.out.println("Responsável não encontrado!");
        }
    }
    
    private void listarTodasTarefas() {
        System.out.println("\n--- LISTA DE TAREFAS ---");
        for (Tarefa t : gerenciadorTarefa.listarTarefas()) {
            System.out.println(t);
        }
    }
    
    private void listarTarefasPorProjeto() {
        listarMeusProjetos();
        System.out.print("ID do projeto: ");
        Long idProjeto = Long.parseLong(scanner.nextLine());
        Projeto projeto = gerenciadorProjeto.buscarPorId(idProjeto);
        
        if (projeto != null) {
            System.out.println("\n--- TAREFAS DO PROJETO: " + projeto.getNome() + " ---");
            for (Tarefa t : gerenciadorTarefa.listarPorProjeto(projeto)) {
                System.out.println(t);
            }
        } else {
            System.out.println("Projeto não encontrado!");
        }
    }
    
    private void listarMinhasTarefas() {
        Usuario usuario = gerenciadorUsuario.getUsuarioLogado();
        System.out.println("\n--- MINHAS TAREFAS ---");
        for (Tarefa t : gerenciadorTarefa.listarPorResponsavel(usuario)) {
            System.out.println(t);
        }
    }
    
    private void atualizarStatusTarefa() {
        listarTodasTarefas();
        System.out.print("ID da tarefa: ");
        Long idTarefa = Long.parseLong(scanner.nextLine());
        
        System.out.println("Novo status:");
        System.out.println("1 - Pendente");
        System.out.println("2 - Em Andamento");
        System.out.println("3 - Concluído");
        System.out.println("4 - Cancelado");
        System.out.print("Opção: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        String novoStatus;
        switch (opcao) {
            case 1: novoStatus = "Pendente"; break;
            case 2: novoStatus = "Em Andamento"; break;
            case 3: novoStatus = "Concluído"; break;
            case 4: novoStatus = "Cancelado"; break;
            default: novoStatus = "Pendente";
        }
        
        gerenciadorTarefa.atualizarStatus(idTarefa, novoStatus);
    }
    
    private void atualizarStatusProjeto() {
        listarMeusProjetos();
        System.out.print("ID do projeto: ");
        Long idProjeto = Long.parseLong(scanner.nextLine());
        
        System.out.println("Novo status:");
        System.out.println("1 - Planejado");
        System.out.println("2 - Em Andamento");
        System.out.println("3 - Concluído");
        System.out.println("4 - Cancelado");
        System.out.print("Opção: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        String novoStatus;
        switch (opcao) {
            case 1: novoStatus = "Planejado"; break;
            case 2: novoStatus = "Em Andamento"; break;
            case 3: novoStatus = "Concluído"; break;
            case 4: novoStatus = "Cancelado"; break;
            default: novoStatus = "Planejado";
        }
        
        gerenciadorProjeto.atualizarStatus(idProjeto, novoStatus);
    }
    
    private void menuRelatorios() {
        System.out.println("\n--- RELATÓRIOS ---");
        System.out.println("1 - Relatório de Desempenho de Projetos");
        System.out.println("2 - Relatório de Carga de Trabalho");
        System.out.println("3 - Voltar");
        System.out.print("Opção: ");
        
        int opcao = Integer.parseInt(scanner.nextLine());
        
        switch (opcao) {
            case 1: gerenciadorProjeto.gerarRelatorioDesempenho(); break;
            case 2: gerenciadorTarefa.gerarRelatorioCargaTrabalho(); break;
            default: System.out.println("Voltando...");
        }
    }
}