import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
    
    private static void edit_submenu(User u)
    {
        int choice;
        Scanner input = new Scanner(System.in);
        while (true)
        {
            ArrayList<String> options = Database.editOptions("User");
            Graphics.printOptions(options, "Escolha o campo que deseja editar");
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
                Graphics.success("Seus dados ficaram assim:\n");
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
        Graphics.start(c.name, c.description);

        while (true)
        {
            ArrayList<String> options = Database.communityOptions(c.isOwner(u));
            Graphics.printOptions(options, "O que deseja fazer?");
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
                    Graphics.failure("Você não tem permissão para fazer isso!");
            }
            else if (choice == 99)
                break;
        }
    }

    private static void menu()
    {
        Scanner input = new Scanner(System.in);
        Graphics.start("iFace", "Comece criando o seu usuário:");
        
        User u = User.createUser();
        int choice = 0;
        
        while (true)
        {
            try{
                Thread.sleep(800);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            if (u == null) {
                ArrayList<String> options = Database.logoutOptions();
                Graphics.printOptions(options, "Você não está logado no iFace, o que deseja?");
            }
            else {
                ArrayList<String> options = Database.mainMenuOptions();
                Graphics.printOptions(options, "Olá " + u.nickname + ", o que deseja?");
            }
            System.out.println("[99] Encerrar iFace");
            
            choice = input.nextInt();
            
            if (choice == 1){
                u = User.createUser();
            }
            else if (choice == 2)
            {
                if (u == null)
                    u = User.login();
                else
                    u = null;
            }
            else if (choice == 3){
                System.out.println("\n[1] Criar uma comunidade");
                System.out.println("[2] Entrar numa comunidade");
                choice = input.nextInt();
                if (choice == 1)
                    u.newCommunity();
                else
                    u.enterCommunity();
            }
            else if (choice == 4)
                edit_submenu(u);
            else if (choice == 5)
            {
                System.out.println("\nDigite o nome do amigo que deseja adicionar:");
                String nickname = input.next().trim();
                u.friendRequest(nickname);
            }
            else if (choice == 6)
                u.updateFriendList();
            else if (choice == 7)
                u.showFriends();
            else if (choice == 8)
            {
                ArrayList<String> options = Database.messageOptions();
                Graphics.printOptions(options, "Como deseja enviar?");
                choice = input.nextInt();
                if (choice == 1) {
                    System.out.println("Para quem deseja enviar uma mensagem?");
                    String receiver = input.next();
                    u.newMessage(receiver);
                }
                else if (choice == 2)
                    u.newMessage();
                else
                    u.feed.newMessage(u, User.getUsers());
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
            else if (choice == 12) {
                u.feed.showFeed();
            }
            else if (choice == 13)
            {
                u.deleteUser();
                u = null;
            }
            else if (choice == 99)
                break;
            }
    }
    
    public static void main(String[] args) {
        menu();
    }
}