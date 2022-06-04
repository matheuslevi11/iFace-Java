# iFace Java

Implementação de um sistema de rede social em Java

## Instruções de execução
Execute a classe Menu para iniciar a aplicação.
## Diagrama de classes

<img src="https://github.com/matheuslevi11/iFace-Java/blob/main/Assets/diagrama.png">

## Code Smells Encontrados no código

### Long Method 

Os seguintes métodos são mais complicados do que a maioria e possuem muitas linhas

- User.deleteUser(User u)
- Menu.menu()

### Long Class

As seguintes classes são mais complicadas do que a maioria e possuem muito código.

- User
- Menu

### Primitive Obsession

Na função abaixo, foi utilizado uma string onde poderia ter sido usado um objeto.

- Community.deleteUser(User u)

### Long Parameter List

- iFace.readIntegerField(Scanner input, int min, int max, Boolean isMenu)

### Switch Statements

Todas as funções de menu possuem vários if seguidos com as opções de escolha

### Shotgun Surgery

As funções da classe iFace são usadas em todo o código, por isso teriam de ser alteradas
em diversos lugares em caso de mudança

### Data Class

- Database

### Speculative Generality

- interface Friend