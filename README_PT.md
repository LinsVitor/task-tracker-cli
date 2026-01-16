# Task Tracker CLI

Uma aplicação simples de interface de linha de comando (CLI) para rastreamento de tarefas, escrita em Java. Este projeto demonstra uma implementação em Java puro, sem o uso de bibliotecas ou frameworks externos, gerenciando a persistência de dados através de um arquivo JSON.

## Funcionalidades

- **Adicionar Tarefa**: Cria uma nova tarefa com uma descrição.
- **Atualizar Tarefa**: Modifica a descrição de uma tarefa existente.
- **Deletar Tarefa**: Remove uma tarefa pelo seu ID.
- **Marcar Status**: Altera o status de uma tarefa para `TODO`, `IN_PROGRESS` ou `DONE`.
- **Listar Tarefas**:
    - Listar todas as tarefas.
    - Listar tarefas por status (todo, done, in_progress).
- **Persistência de Dados**: As tarefas são salvas automaticamente em um arquivo JSON (`data/tasks.json`).

## Pré-requisitos

- Java Development Kit (JDK) 21 ou superior.

## Como Compilar e Executar

1.  **Compilar o código fonte:**
    Abra um terminal no diretório raiz do projeto e execute:
    ```bash
    javac -d out src/com/tasktracker/Main.java src/com/tasktracker/cli/*.java src/com/tasktracker/exception/*.java src/com/tasktracker/model/*.java src/com/tasktracker/repository/*.java src/com/tasktracker/service/*.java src/com/tasktracker/util/*.java
    ```

2.  **Executar a aplicação:**
    Use o comando `java` para executar as classes compiladas. Você precisa passar os argumentos como se estivesse digitando na CLI.

    *Exemplo: Adicionando uma tarefa*
    ```bash
    java -cp out com.tasktracker.Main task-cli add "Comprar mantimentos"
    ```

    *Exemplo: Listando tarefas*
    ```bash
    java -cp out com.tasktracker.Main task-cli list
    ```

## Exemplos de Uso

A aplicação espera que o primeiro argumento seja `task-cli`, seguido pelo comando e argumentos.

*   **Adicionar uma nova tarefa:**
    ```bash
    task-cli add "Finalizar o projeto"
    ```
*   **Listar todas as tarefas:**
    ```bash
    task-cli list
    ```
*   **Listar tarefas por status:**
    ```bash
    task-cli list done
    ```
*   **Atualizar uma tarefa (ID 1):**
    ```bash
    task-cli update 1 "Finalizar o README"
    ```
*   **Marcar uma tarefa como em progresso (ID 1):**
    ```bash
    task-cli mark 1 in_progress
    ```
*   **Deletar uma tarefa (ID 1):**
    ```bash
    task-cli delete 1
    ```

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas:

*   `src/com/tasktracker/`
    *   `Main.java`: Ponto de entrada da aplicação.
    *   `cli/`: Lida com a análise de comandos e interação com o usuário.
    *   `service/`: Contém a lógica de negócios.
    *   `repository/`: Lida com a persistência de dados (E/S de arquivo).
    *   `model/`: Classes de dados (`Task`, `Status`).
    *   `util/`: Classes utilitárias (ex: manipulação de JSON).
