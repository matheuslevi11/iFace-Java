# iFace Java

Implementação de um sistema de rede social em Java

## Instruções de execução
Execute a classe Menu para iniciar a aplicação.

## Refatoração

### Command
Para melhorar o entendimento e legibilidade dos menus, foi criada uma interface Submenu para obrigar as classes que necessitam a implementar uma função de submenu, assim, é possível organizar melhor.

### Extract Method
A função User.deleteUser() era muito grande e tinha responsabilidade de deletar diversos objetos de um usuário, portanto, foram criados dois métodos para refatorar esta função:
- User.deleteFriends()
- User.deleteCommunity()

### Introduce Null Objects
Existem muitas situações em que a classe User aparece como um objeto nulo, então, para melhorar a qualidade do código, é possível criar um null object para a classe.

## Diagrama de classes

<img src="https://github.com/matheuslevi11/iFace-Java/blob/main/Assets/diagrama.png">

