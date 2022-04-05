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
    public ArrayList<Message> msgs = new ArrayList<Message>();
    private Community community = null;
    // Variáveis estáticas
    public static ArrayList<User> users = new ArrayList<User>();

    // Construtor
    public User(String nickname, String login, String password)
    {
        this.nickname = nickname;
        this.login = login;
        this.password = password;
    }

    // Funções de visualização
    public void printUserInfo()
    {
        System.out.println("Apelido: " + this.nickname);
        
        String[] atributes = this.profile.getProfileInfo();
        System.out.println("Hobbie: " + atributes[0]);
        System.out.println("Idade: " + atributes[1]);
        System.out.println("Local: " + atributes[2]);
        
        System.out.println("Amigos: " + friends.size());
        
        if (this.community != null) {
            System.out.println("Comunidade: " + this.community.name);
        }
    }
    
    public void showFriends()
    {
        System.out.println("Seus amigos:\n");
        for (User u : friends)
        {
            System.out.println(u.nickname);
        }
    }

    // Funções de Edição
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

    
    // Funções de amizade
    public void friendRequest(String nickname)
    {
        User u = getUser(nickname);
        if (u != null)
        {
            u.requests.add(this);
            System.out.println("Solicitação enviada!");
        }
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
        if (u != null)
        {
            u.friends.add(this);
            this.friends.add(u);
            this.requests.remove(u);
            System.out.println("Amigo adicionado!");
        }
        else
            System.out.println("Erro ao adicionar amigo!");
    }

    // Funções de mensagem
    public void newMessage(String receiver)
    {
        User u = getUser(receiver);
        if (u != null) {
            Message msg = Message.sendMessage(this.nickname);
            u.msgs.add(msg);
            System.out.println("Mensagem enviada!");
        }
        else
            System.out.println("Usuário não encontrado!");
    }
    public void newMessage()
    {
        if (this.community != null) {
            Message msg = Message.sendMessage(this.nickname);
            this.community.msgs.add(msg);
            System.out.println("Mensagem enviada!");
        }
        else
            System.out.println("Você não possui uma comunidade!");
    }

    public void showMessages()
    {
        if (msgs.size() > 0)
        {
            System.out.println("Suas mensagens:\n");
            for (Message m : msgs) {
                System.out.println(m.getDate() + " " + m.getSender() + ": " + m.getContent());
            }
        }
        else
            System.out.println("Você não tem mensagens");
    }

    // Funções de comunidade
    public void newCommunity()
    {
        this.community = Community.createCommunity(this);
    }
    public Community getCommunity()
    {
        return this.community;
    }
    public void enterCommunity()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Digite o nome da comunidade que deseja entrar:");
        String name = input.next();
        Community.enterCommunity(name, this);
    }
    public void enterCommunity(Community c)
    {
        this.community = c;
    }
    // Funções estáticas
    private static User getUser(String nickname)
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

    public static User createUser() {    
        Scanner input = new Scanner(System.in);
        String nickname; String login; String password;

        while (true)
        {
            System.out.println("Digite o seu Apelido:");
            nickname = input.next().trim();
            System.out.println("Digite o seu Login:");
            login = input.next().trim();
            System.out.println("Digite a sua Senha:");
            password = input.next().trim();

            if (getUser(nickname) == null)
                break;
            System.out.println("Apelido já existe, tente novamente!\n");
        }

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

    public static void search()
    {
        while (true) {
            Scanner input = new Scanner(System.in);
            
            System.out.println("Digite o nome de um usuário:\n");
            String nick = input.next();
            System.out.println("------------------------------");
            User u = getUser(nick);
            if (u != null)
            {
                u.printUserInfo();
                System.out.println("------------------------------");
            }
            else
            System.out.println("Usuário não encontrado!");
            
            System.out.println("[1] Continuar buscando\n[2] Sair\n");
            int choice = input.nextInt();
            if (choice == 2) {
                break;
            }
        }
    }
}