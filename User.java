import java.util.Scanner;

public class User {
    
    private String nickname;
    private String login;
    private String password;

    private static User[] users = new User[10];
    private static int userCount = 0;

    public User(String nickname, String login, String password)
    {
        this.nickname = nickname;
        this.login = login;
        this.password = password;
    }

    public void printUserInfo()
    {
        System.out.println("Apelido: " + this.nickname);
        System.out.println("Usuario: " + this.login);
        System.out.println("Senha: " +this.password + "\n");
    }

    public void editNickname(String nickname)
    {
        if (nickname.length() > 0)
            this.nickname = nickname;
    }

    public void editLogin(String login)
    {
        if (login.length() > 0)
            this.login = login;
    }
    
    public void editPassword(String password)
    {
        if (password.length() > 0)
            this.password = password;
    }

    public static void showAllUsers()
    {
        System.out.println("Mostrando todos os usuários logados:");
        for (int i = 0; i < userCount; i++)
        {
            users[i].printUserInfo();
        }
    }
    
    public static User createUser() {
        if (userCount == 10)
        {
            System.out.println("Limite de usuários atingido!");
            return null;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o seu Apelido:");
        String nickname = input.next().trim();
        System.out.println("Digite o seu Login:");
        String login = input.next().trim();
        System.out.println("Digite a sua Senha:");
        String password = input.next().trim();
        
        User u = new User(nickname, login, password);
        users[userCount] = u;
        userCount++;
        return u;
    }
    
    public static User login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o seu Login:");
        String login = input.nextLine().trim();
        System.out.println("Digite a sua Senha:");
        String password = input.nextLine().trim();
        
        for (int i = 0; i < userCount; i++)
        {
            System.out.println("login " + users[i].login + " " + login);
            System.out.println("pass " + users[i].password + " " + password);
            if (login.equals(users[i].login) && password.equals(users[i].password))
            {
                User u = users[i];
                System.out.println("Logado com sucesso!\n");
                return u;
            }
        }
        
        System.out.println("Falha no login!\n");
        return null;
    }
}