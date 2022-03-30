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
        System.out.println(this.nickname);
        System.out.println(this.login);
        System.out.println(this.password);
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
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o seu Apelido:");
        String nickname = input.nextLine();
        System.out.println("Digite o seu Login:");
        String login = input.nextLine();
        System.out.println("Digite a sua Senha:");
        String password = input.nextLine();

        User u = new User(nickname, login, password);
        users[userCount] = u;
        userCount++;
        return u;
    }
}