import java.util.Scanner;

public class Menu {
    
    private static void edit_submenu(User u)
    {
        int choice;
        Scanner input = new Scanner(System.in);
        while (true)
        {
            System.out.println("\nQual campo deseja alterar?\n");
            System.out.println("[1] Apelido");
            System.out.println("[2] Login");
            System.out.println("[3] Senha");
            System.out.println("[4] Editar Perfil");
            System.out.println("[99] Sair da edição");
            
            choice = input.nextInt();

            if (choice == 1)
            {
                System.out.println("Digite o novo apelido:");
                String nickname = input.next().trim();
                u.editNickname(nickname);
            }
            else if (choice == 2)
            {
                System.out.println("Digite o novo login:");
                String login = input.next().trim();
                u.editLogin(login);
            }
            else if (choice == 3)
            {
                System.out.println("Digite a nova senha:");
                String password = input.next().trim();
                u.editPassword(password);
            }
            else if (choice == 4)
            {
                u.profile.editProfile();
            }
            else if (choice == 99)
            {
                System.out.println("Seus dados ficaram assim:\n");
                u.printUserInfo();
                break;
            }
        }
    }
    
    private static void community_submenu(User u)
    {
        Scanner input = new Scanner(System.in);
        int choice;
        Community c = u.getCommunity();
        System.out.println("------------ Bem vindo a comunidade " + c.name + " ------------\n");
        System.out.println(c.description);

        while (true)
        {
            System.out.println("\n[1] Ver mensagens da comunidade");
            if (c.isOwner(u))
            {
                System.out.println("[2] Gerenciar comunidade");
            }
            System.out.println("[99] Sair da aba de comunidade");
            
            choice = input.nextInt();

            if (choice == 1)
            {
                c.showMessages();
            }
            else if (choice == 2)
            {
                if (c.isOwner(u)) {
                    c.ManageCommunity();
                }
                else
                    System.out.println("Você não tem permissão para fazer isso!");
            }
            else if (choice == 99)
                break;
        }
    }

    private static void menu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("------------ Bem vindo ao iFace ------------\n");
        
        System.out.println("Ainda não há usuários cadastrados no sistema, digite 1 para criar o seu usuário!");
        int choice = input.nextInt();
        
        while (choice != 1)
        {
            System.out.println("Ainda não há usuários cadastrados no sistema, digite 1 para criar o seu usuário!");
            choice = input.nextInt();
        }
        
        
        User u = User.createUser();
        
        while (true)
        {
            try{
                Thread.sleep(800);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            if (u == null)
            {
                System.out.println("[1] Criar outro usuário");
                System.out.println("[2] Ver todos os usuários");
                System.out.println("[3] Fazer login") ;
            }
            else
            {
                System.out.println("\nOlá " + u.nickname + ", o que deseja?\n");
                System.out.println("[1] Criar outro usuário");
                System.out.println("[2] Criar/Entrar em uma comunidade");
                System.out.println("[3] Fazer logout");
                System.out.println("[4] Editar Perfil");
                System.out.println("[5] Adicionar Amigo");
                System.out.println("[6] Ver solicitações de amizade");
                System.out.println("[7] Ver lista de amigos");
                System.out.println("[8] Enviar uma mensagem");
                System.out.println("[9] Ver caixa de mensagens");
                System.out.println("[10] Ir para o menu da comunidade");
                System.out.println("[11] Pesquisar usuário");
            }
            System.out.println("[99] Encerrar iFace");
            
            choice = input.nextInt();
            
            if (choice == 1)
            u = User.createUser();
            else if (choice == 2)
            {
                System.out.println("[1] Criar uma comunidade");
                System.out.println("[2] Entrar numa comunidade");
                choice = input.nextInt();
                if (choice == 1)
                    u.newCommunity();
                else
                    u.enterCommunity();
            }
            else if (choice == 3)
                if (u == null)
                    u = User.login();
                else
                    u = null;
            else if (choice == 4)
                edit_submenu(u);
            else if (choice == 5)
            {
                System.out.println("Digite o nome do amigo que deseja adicionar:");
                String nickname = input.next().trim();
                u.friendRequest(nickname);
            }
            else if (choice == 6)
                u.updateFriendList();
            else if (choice == 7)
                u.showFriends();
            else if (choice == 8)
            {
                System.out.println("[1] Enviar uma mensagem para o usuário");
                System.out.println("[2] Enviar uma mensagem para uma comunidade");
                choice = input.nextInt();
                if (choice == 1) {
                    System.out.println("Para quem deseja enviar uma mensagem?");
                    String receiver = input.next();
                    u.newMessage(receiver);
                }
                else
                    u.newMessage();
            }
            else if (choice == 9)
            {
                u.showMessages();
            }
            else if (choice == 10)
            {
                if (u.getCommunity() != null)
                    community_submenu(u);
                else
                    System.out.println("Você não está em uma comunidade!");
            }
            else if (choice == 11)
                User.search();
            else if (choice == 99)
                break;
            }
    }
    
    public static void main(String[] args) {
        menu();
    }
}