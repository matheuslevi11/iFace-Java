import java.util.Scanner;
import java.util.ArrayList;

public class User {
    // Usuário
    private String nickname;
    private String login;
    private String password;
    // Amizade
    public ArrayList<User> friends = new ArrayList<User>();
    public ArrayList<User> requests = new ArrayList<User>();
    // Outros objetos
    private Profile profile;
    // Variáveis estáticas
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
        
        String[] atributes = this.profile.getProfileInfo();
        System.out.println("Hobbie: " + atributes[0] + "\n");
        System.out.println("Idade: " + atributes[1] + "\n");
        System.out.println("Local: " + atributes[2] + "\n");
    }
    
    public void showFriends()
    {
        for (int i = 0; i < friends.size(); i++)
        {
            System.out.println(friends.get(i).nickname);
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

    public void editHobbie(String hobbie)
    {
        this.profile.hobbie = hobbie;
    }

    public void editIdade(int idade)
    {
        this.profile.idade = idade;
    }

    public void editLocal(String local)
    {
        this.profile.local = local;
    }

    private User getUser(String nickname)
    {
        for (int i = 0; i < userCount; i++)
        {
            if (nickname.equals(users[i].nickname))
            {
                return users[i];
            }
        }
        return null;
    }

    public void friendRequest(String nickname)
    {
        User u = getUser(nickname);
        u.requests.add(this);

    }

    public void updateFriendList()
    {
        System.out.println("Você tem " + requests.size() + "solicitações");
        int choice;
        Scanner input = new Scanner(System.in);
        
        if (requests.size() > 0)
        {
            
            while (true)
            {
                for (int i = 0; i < requests.size(); i++)
                {
                    System.out.println("Pedido de amizade de: " + requests.get(i).nickname);
                }
                System.out.println("[1] Aceitar Solicitação");
                System.out.println("[2] Sair");
                choice = input.nextInt();
                if (choice == 1)
                {
                    System.out.println("Digite o  nome do usuario que deseja aceitar");
                    String newFriend = input.next();
                    this.addFriend(newFriend);
                }
                else {
                    break;
                }
            }
        }
    }

    public void addFriend(String newFriend)
    {
        User u = getUser(newFriend);
        u.friends.add(this);
        this.friends.add(u);
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
        u.profile = Profile.createProfile();
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