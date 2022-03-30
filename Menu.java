import java.util.Scanner;

public class Menu {
    
    private static void menu()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("------------ Bem vindo ao iFace ------------\n");
        System.out.println("O que deseja?");
        
        System.out.println("Você não está logado, digite 1 para criar o seu usuário!");
        int choice = input.nextInt();
        
        while (choice != 1)
        {
            System.out.println("Você não está logado, digite 1 para criar o seu usuário!");
            choice = input.nextInt();
        }
        
        
        User created = User.createUser();
        
        while (true)
        {
            System.out.println("O que deseja?\n");
            System.out.println("[1] Criar outro usuário");
            System.out.println("[2] Ver todos os usuários");
            System.out.println("[3] Sair");
            
            choice = input.nextInt();

            if (choice == 1)
                created = User.createUser();
            else if (choice == 2)
                User.showAllUsers();
            else if (choice == 3)
                break;
        }
    }
    public static void main(String[] args) {
        menu();
    }
}