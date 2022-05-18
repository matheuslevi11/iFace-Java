import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class User implements Friend, Constants {
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
    public Feed feed = new Feed();
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
        System.out.println(BLUE+"Apelido: " + this.nickname+RESET);
        
        String[] atributes = this.profile.getProfileInfo();
        System.out.println("Hobbie: " + atributes[0]);
        System.out.println("Idade: " + atributes[1]);
        System.out.println("Local: " + atributes[2]);
        
        System.out.println("Amigos: " + friends.size());
        
        if (this.community != null) {
            System.out.println("Comunidade: " + this.community.name);
        }
    }

    @Override
    public void showFriends()
    {
        System.out.println(BLUE+"---------------------------------------------------------------------------"+RESET);
        System.out.println(PURPLE+"Seus amigos:"+RESET);
        System.out.println(BLUE+"---------------------------------------------------------------------------"+RESET);
        for (User u : friends) {
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

    public void deleteUser()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Você tem certeza que deseja deletar seu usuário?");
        System.out.println("[1] Sim\n[2] Não");
        int choice = iFace.readIntegerField(input, 1, 2, false);
        if (choice == 1) {
            for (User u : users) {
                // Removendo mensagens
                Iterator<Message> itr = u.msgs.iterator(); 
                while (itr.hasNext()) { 
                    Message m = itr.next(); 
                    if (m.getSender().equalsIgnoreCase(this.nickname)) { 
                        itr.remove(); 
                    } 
                }
                // Removendo relacionamentos
                u.friends.remove(this);
            }
            // Removendo comunidade
            if (this.community != null) {
                String c = this.community.deleteUser(this);
                if (c.equalsIgnoreCase("deleted")) {
                    for (User u : users) {
                        if (u.community != null) {
                            if (u.community.name.equals(this.community.name)) {
                                u.community = null;
                            }
                        }
                    }
                }
                else
                {
                    User newOwner = getUser(c);
                    this.community.setOwner(newOwner);
                }
            }
            this.profile = null;
            users.remove(this);
        }
    }
    // Funções de amizade
    @Override
    public void friendRequest(String nickname)
    {
        User u = getUser(nickname);
        if (u != null)
        {
            u.requests.add(this);
            Graphics.success("Solicitação enviada!\n");
        }
        else
        Graphics.failure("Usuário não encontrado!\n");
    }
    @Override
    public void updateFriendList()
    {
        System.out.println(BLUE+"---------------------------------------------------------------------------"+RESET);
        System.out.println(PURPLE+"Você tem " + requests.size() + " solicitações"+RESET);
        System.out.println(BLUE+"---------------------------------------------------------------------------"+RESET);

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
                choice = iFace.readIntegerField(input, 1, 2, false);
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
    }
    @Override
    public void addFriend(String newFriend)
    {
        User u = getUser(newFriend);
        if (u != null)
        {
            u.friends.add(this);
            this.friends.add(u);
            this.requests.remove(u);
            Graphics.success("Amigo adicionado!");
        }
        else
        Graphics.failure("Erro ao adicionar amigo!");
    }

    // Funções de mensagem
    public void newMessage(String receiver)
    {
        User u = getUser(receiver);
        if (u != null) {
            Message msg = Message.sendMessage(this.nickname);
            u.msgs.add(msg);
            Graphics.success("Mensagem enviada!");
        }
        else
            Graphics.failure("Usuário não encontrado!");
    }
    public void newMessage()
    {
        if (this.community != null) {
            Message msg = Message.sendMessage(this.nickname);
            this.community.msgs.add(msg);
            Graphics.success("Mensagem enviada!");
        }
        else
            Graphics.failure("Você não possui uma comunidade!");
    }
    public void showMessages()
    {
        if (msgs.size() > 0)
        {
            System.out.println(PURPLE+"Suas mensagens:\n"+RESET);
            for (Message m : msgs) {
                System.out.println(m.getDate() + " " + m.getSender() + ": " + m.getContent());
            }
        }
        else
            Graphics.failure("Você não tem mensagens");
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
        Scanner messageReceiver = new Scanner(System.in);
        messageReceiver.useDelimiter("\n");
        System.out.println("Digite o nome da comunidade que deseja entrar:");
        String name = messageReceiver.next();
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
            if (nickname.equalsIgnoreCase(u.nickname))
            {
                return u;
            }
        }
        return null;
    }

    public static String readNickname(Scanner input, Boolean search) {
        Boolean read = false;
        String nickname = "";
        while (!read)
        {
            try {
                nickname = input.next().trim();
                if (nickname.isEmpty()) {
                    throw new Exception("Nome de usuário não pode estar vazio!");
                }
                if (getUser(nickname) == null)
                {
                    if (search) throw new Exception("Nome de usuário não encontrado!");
                }
                else if (!search) throw new Exception("Nome de usuário já existe!");
                break;
            } catch (Exception e) {
                if (e.getMessage() != null)
                    Graphics.failure(e.getMessage());
                input.next();
            }
        }
        return nickname;
    }

    public static User createUser() {    
        Scanner input = new Scanner(System.in);
        String nickname; String login; String password;

        System.out.println("Digite o seu Apelido:");
        nickname = readNickname(input, false);
        System.out.println("Digite o seu Login:");
        login = input.next().trim();
        System.out.println("Digite a sua Senha:");
        password = input.next().trim();

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
                Graphics.success("Logado com sucesso!\n");
                return u;
            }
        }
        
        Graphics.failure("Falha no login!\n");
        return null;
    }

    public static void search()
    {
        while (true) {
            Scanner input = new Scanner(System.in);
            
            System.out.println("Digite o nome de um usuário:\n");
            String nickname = readNickname(input, true);
            System.out.println("------------------------------");
            User u = getUser(nickname);
            u.printUserInfo();
            System.out.println("------------------------------");
            
            System.out.println("[1] Continuar buscando\n[2] Sair\n");
            int choice = iFace.readIntegerField(input, 1, 2, false);
            if (choice == 2) {
                break;
            }
        }
    }
    public static ArrayList<User> getUsers()
    {
        return users;
    }
}