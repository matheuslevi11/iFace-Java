import java.util.Scanner;
import java.util.ArrayList;

public class Menu {

    private static void menu()
    {
        Scanner input = new Scanner(System.in);
        Graphics.start("iFace", "Comece criando o seu usuário:");
        
        User u = User.createUser();
        int choice = 0;
        
        while (true)
        {
            int num_options = 0;
            try{
                Thread.sleep(800);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            if (u instanceof NullUserObject) {
                ArrayList<String> options = Database.logoutOptions();
                num_options += options.size();
                Graphics.printOptions(options, "Você não está logado no iFace, o que deseja?");
            }
            else {
                ArrayList<String> options = Database.mainMenuOptions();
                num_options += options.size();
                Graphics.printOptions(options, "Olá " + u.nickname + ", o que deseja?");
            }
            System.out.println("[99] Encerrar iFace");
            
            choice = iFace.readIntegerField(input, 1, num_options, true);
            
            if (choice == 1){
                u = User.createUser();
            }
            else if (choice == 2)
            {
                if (u instanceof NullUserObject)
                    u = User.login();
                else
                    u = new NullUserObject();
            }
            else if (choice == 3){
                System.out.println("\n[1] Criar uma comunidade");
                System.out.println("[2] Entrar numa comunidade");
                choice = iFace.readIntegerField(input, 1, 2, false);
                if (choice == 1)
                    u.newCommunity();
                else
                    u.enterCommunity();
            }
            else if (choice == 4)
                u.edit();
            else if (choice == 5)
            {
                boolean requested = false;
                while(!requested) {
                    System.out.println("\nDigite o nome do amigo que deseja adicionar:");
                    String nickname = iFace.readStringField(input);
                    requested = u.friendRequest(nickname);
                }
            }
            else if (choice == 6)
                u.updateFriendList();
            else if (choice == 7)
                u.showFriends();
            else if (choice == 8)
            {
                ArrayList<String> options = Database.messageOptions();
                Graphics.printOptions(options, "Como deseja enviar?");
                choice = iFace.readIntegerField(input, 1, options.size(), false);
                if (choice == 1) {
                        System.out.println("Para quem deseja enviar uma mensagem?");
                        String receiver = input.next();
                        try{
                            if (!u.newMessage(receiver))
                            {
                                throw new UserNotFoundException();
                            }
                        }
                        catch (UserNotFoundException unf)
                        {
                            System.out.println("Tente novamente");
                        }
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
                    u.community.edit();
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
                u = new NullUserObject();
            }
            else if (choice == 99)
                break;
            }
    }
    
    public static void main(String[] args) {
        menu();
    }
}