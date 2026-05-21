# Sistema de Gestão de Projetos

## Sobre o Projeto

Este sistema foi desenvolvido como parte da disciplina **Programação de Soluções Computacionais** da **Universidade Anhembi Morumbi**. Trata-se de uma solução em Java para gerenciamento de projetos, equipes e tarefas, atendendo a uma demanda real de empresas que trabalham com desenvolvimento de software.

### Problema Solucionado
Empresas que gerenciam múltiplas projetos enfrentam dificuldades para:
- Controlar etapas e distribuir tarefas aos colaboradores
- Acompanhar prazos de entrega
- Alocar equipes de forma eficiente
- Gerar relatórios de desempenho

### Solução
Um sistema com perfis de acesso (Administrador, Gerente, Colaborador) que permite:
- Cadastro e gerenciamento de usuários
- Criação e acompanhamento de projetos
- Formação de equipes multidisciplinares
- Atribuição e controle de tarefas
- Geração de relatórios de desempenho

---

## ✅ Requisitos Atendidos

| Requisito | Descrição | Status |
|-----------|-----------|--------|
| 1a | Cadastro de Usuários (nome, CPF, e-mail, cargo, login, senha) | ✅ |
| 1b | Perfis: administrador, gerente, colaborador | ✅ |
| 2a | Cadastro de Projetos (nome, descrição, datas, status) | ✅ |
| 2b | Cada projeto com gerente responsável | ✅ |
| 3a | Cadastro de Equipes (nome, descrição, membros) | ✅ |
| 3b | Equipe pode atuar em vários projetos | ✅ |
| Extra | Distribuição de tarefas aos colaboradores | ✅ |
| Extra | Relatórios de desempenho e carga de trabalho | ✅ |

---

## Tecnologias Utilizadas

| Componente | Tecnologia |
|------------|------------|
| **Linguagem** | Java 21 LTS (OpenJDK) |
| **Paradigma** | Orientação a Objetos (Abstração, Encapsulamento, Herança, Polimorfismo) |
| **Arquitetura** | MVC (Model-View-Controller) |
| **Controle de versão** | Git + GitHub |
| **Ambiente de desenvolvimento** | GitHub Codespaces / IntelliJ IDEA |
| **Interface** | Console (terminal) |

### Estrutura do Projeto (MVC)
Sistema-Gestao-Projetos-Java/
├── src/com/manager/
│ ├── model/ # Classes de domínio
│ │ ├── Usuario.java
│ │ ├── Projeto.java
│ │ ├── Equipe.java
│ │ ├── Tarefa.java
│ │ └── Perfil.java
│ ├── service/ # Regras de negócio
│ │ ├── GerenciadorUsuario.java
│ │ ├── GerenciadorProjeto.java
│ │ ├── GerenciadorEquipe.java
│ │ └── GerenciadorTarefa.java
│ └── view/ # Interface com usuário
│ └── MenuConsole.java
└── Main.java # Ponto de entrada

text

---

## Como Executar

### Pré-requisitos

- **Java 21** ou superior instalado
- Terminal (Linux/Mac) ou PowerShell/CMD (Windows)
- Git (opcional, para clonar)

### Passo a passo

1. **Clone o repositório**
```bash
git clone https://github.com/luansantiagomanager-commits/Sistema-Gestao-Projetos-Java.git
cd Sistema-Gestao-Projetos-Java
Compile o código

bash
javac -d . $(find . -name "*.java")
Execute o programa

bash
java com.manager.Main
Credenciais de Acesso Padrão
Perfil	Login	Senha
Administrador	admin	admin123
!! Importante: O administrador pode cadastrar novos usuários com perfis de gerente ou colaborador.

Como Usar
Menu do Administrador
Opção	Funcionalidade
1	Cadastrar Usuário
2	Listar Usuários
3	Cadastrar Projeto
4	Listar Projetos
5	Cadastrar Equipe
6	Listar Equipes
7	Gerenciar Tarefas
8	Relatórios
9	Logout
0	Sair

Menu do Gerente
Opção	Funcionalidade
1	Meus Projetos
2	Criar Projeto
3	Atualizar Status do Projeto
4	Criar Tarefa
5	Listar Tarefas do Projeto
6	Relatórios
7	Logout

Menu do Colaborador
Opção	Funcionalidade
1	Minhas Tarefas
2	Atualizar Status da Tarefa
3	Logout

Exemplos de Uso
Cadastrar um Projeto
text
Nome do projeto: Sistema de Gestão Acadêmica
Descrição: Controle de notas e frequência de alunos
Data de início: 2026-06-01
Data de término prevista: 2026-12-15
ID do gerente: 2
Formato de Datas
As datas devem ser informadas no formato ISO: AAAA-MM-DD

✅ Correto: 2026-05-21

❌ Incorreto: 2026/05/21 ou 21/05/2026

Princípios de Orientação a Objetos Aplicados
Princípio	Aplicação no Projeto
Abstração	Classes Usuario, Projeto, Equipe, Tarefa modelam entidades reais
Encapsulamento	Atributos privados com getters/setters que validam regras de negócio
Herança	Classe base Usuario com atributos comuns; perfis herdam comportamentos
Polimorfismo	Métodos como gerarRelatorio() têm comportamentos diferentes por perfil

Evoluções Futuras
O sistema foi arquitetado em camadas (MVC), o que permite futuras evoluções sem reescrever o núcleo de negócio:

Persistência em Banco de Dados (PostgreSQL)

API REST com Spring Boot

Interface Web (React/Angular)

Testes Unitários com JUnit

Logging estruturado com SLF4J

Autor
Luan Santiago
Disciplina: Programação de Soluções Computacionais
Universidade Anhembi Morumbi - Digital 2026-1




