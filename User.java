import java.util.Scanner;
import java.util.ArrayList;

public class User {
    // Usuário
    public String nickname;
    private String login;
    private String password;
    // Amizade
    public ArrayList<User> friends = new ArrayList<User>();
    public ArrayList<User> requests = new ArrayList<User>();
    // Outros objetos
    public Profile profile;
    // Variáveis estáticas
    public static ArrayList<User> users = new ArrayList<User>();

    // Construtor
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
        
        String[] atributes = this.profile.getProfileInfo();
        System.out.println("Hobbie: " + atributes[0]);
        System.out.println("Idade: " + atributes[1]);
        System.out.println("Local: " + atributes[2]);
    }
    
    public void showFriends()
    {
        System.out.println("Seus amigos:\n");
        for (User u : friends)
        {
            System.out.println(u.nickname);
        }
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

    private User getUser(String nickname)
    {
        for (User u: users)
        {
            if (nickname.equals(u.nickname))
            {
                return u;
            }
        }
        return null;
    }

    public void friendRequest(String nickname)
    {
        User u = getUser(nickname);
        if (u != null)
            u.requests.add(this);
        else
            System.out.println("Usuário não encontrado!");
    }

    public void updateFriendList()
    {
        System.out.println("---------------------------------------------");
        System.out.println("Você tem " + requests.size() + " solicitações\n");
        int choice;
        Scanner input = new Scanner(System.in);
        if (requests.size() > 0)
        {
            while (true)
            {
                for (User u: requests)
                {
                    System.out.println("Pedido de amizade de: " + u.nickname);
                }
                System.out.println("[1] Aceitar Solicitação");
                System.out.println("[2] Sair");
                choice = input.nextInt();
                if (choice == 1)
                {
                    System.out.println("Digite o nome do usuario que deseja aceitar");
                    String newFriend = input.next();
                    this.addFriend(newFriend);
                }
                else {
                    break;
                }
            }
        }
        System.out.println("---------------------------------------------");
    }

    public void addFriend(String newFriend)
    {
        User u = getUser(newFriend);
        u.friends.add(this);
        this.friends.add(u);
        this.requests.remove(u);
    }
  
    // Funções estáticas
    public static void showAllUsers()
    {
        System.out.println("Mostrando todos os usuários logados:");
        for (User u: users)
        {
            u.printUserInfo();
        }
    }
    
    public static User createUser() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o seu Apelido:");
        String nickname = input.next().trim();
        System.out.println("Digite o seu Login:");
        String login = input.next().trim();
        System.out.println("Digite a sua Senha:");
        String password = input.next().trim();
        
        User u = new User(nickname, login, password);
        u.profile = Profile.createProfile();
        users.add(u);
        return u;
    }
    
    public static User login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o seu Login:");
        String login = input.nextLine().trim();
        System.out.println("Digite a sua Senha:");
        String password = input.nextLine().trim();
        
        for (User u: users)
        {
            if (login.equals(u.login) && password.equals(u.password))
            {
                System.out.println("Logado com sucesso!\n");
                return u;
            }
        }
        
        System.out.println("Falha no login!\n");
        return null;
    }
}