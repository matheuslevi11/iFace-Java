import java.util.Scanner;
import java.util.ArrayList;

public class Community {
    public String name;
    public String description;
    private User owner;
    public ArrayList<Message> msgs = new ArrayList<Message>();
    public ArrayList<User> members = new ArrayList<User>();
    public ArrayList<User> requests = new ArrayList<User>();

    public static ArrayList<Community> communities = new ArrayList<Community>();

    public Community(String name, String description, User owner)
    {
        this.name = name;
        this.description = description;
        this.owner = owner;
    }

    public boolean isOwner(User u)
    {
        if (u.nickname.equals(this.owner.nickname))
            return true;
        return false;
    }

    public void setOwner(User u)
    {
        this.owner = u;
    }

    public void showMessages()
    {
        if (msgs.size() > 0) {
            System.out.println("---- Mensagens da comunidade ----\n");
            for (Message m : msgs) {
                System.out.println(m.getDate() + " " + m.getSender() + ": " + m.getContent());
            }
        }
        else
            System.out.println("Ainda não há mensagens nesta comunidade");
    }

    public void showMembers()
    {
        for (User u : members) {
            System.out.println("Membro: " + u.nickname);
        }
    }

    public void updateMembers()
    {
        System.out.println("---------------------------------------------");
        System.out.println("Existem " + requests.size() + " solicitações de membros\n");
        int choice;
        Scanner input = new Scanner(System.in);
        if (requests.size() > 0)
        {
            while (true)
            {
                for (User u: requests)
                {
                    System.out.println(u.nickname + " quer se tornar um membro.");
                }
                System.out.println("\n[1] Aceitar Solicitação");
                System.out.println("[2] Sair");
                choice = input.nextInt();
                if (choice == 1)
                {
                    System.out.println("Digite o nome do usuario que deseja aceitar");
                    String newMember = input.next();
                    User addedUser = null;
                    for (User u : requests) {
                        if (u.nickname.equals(newMember))
                        {
                            System.out.println(u.nickname + " foi aceito!");
                            this.members.add(u);
                            u.enterCommunity(this);
                            addedUser = u;
                        }
                    }
                    if (addedUser != null)
                        this.requests.remove(addedUser);
                    
                }
                else
                    break;
            }
        }
        System.out.println("---------------------------------------------");
    }

    public void ManageCommunity()
    {
        Scanner input = new Scanner(System.in);
        int choice;

        while (true)
        {
            System.out.println("\n[1] Ver todos os membros da comunidade");
            System.out.println("[2] Ver novas solicitações de membros");
            System.out.println("[99] Voltar para a aba da comunidade");
            
            choice = input.nextInt();

            if (choice == 1)
            {
                this.showMembers();
            }
            else if (choice == 2)
            {
                this.updateMembers();
            }
            else if (choice == 99)
                break;
        }
    }

    public String deleteUser(User u)
    {
        String newOwner = "deleted";
        if (isOwner(u))
        {
            if (members.size() > 1) {
                System.out.println("Você é dono de uma comunidade, o que deseja fazer com ela?");
                System.out.println("[1] Apagar a comunidade " + this.name);
                System.out.println("[2] Transferir posse");
                Scanner input = new Scanner(System.in);
                int choice = input.nextInt();
                if (choice == 2) {
                    showMembers();
                    System.out.println("Para quem você deseja transferir?");
                    newOwner = input.next();
                }
            }
        }

        this.members.remove(u);
        for (Message m : msgs) {
            if (m.getSender().equals(u.nickname)) {
                msgs.remove(m);
            }
        }
        if (newOwner.equals("deleted")) {
            communities.remove(this);
        }

        return newOwner;
    }

    public static Community createCommunity(User u)
    {
        Scanner input = new Scanner(System.in);
        Scanner messageReceiver = new Scanner(System.in);
        messageReceiver.useDelimiter("\n");
        
        System.out.println("Digite o nome da sua comunidade:");
        String name = input.next();
        System.out.println("Digite a descrição da sua comunidade:");
        String description = messageReceiver.next();
        Community com = new Community(name, description, u);
        com.members.add(u);
        communities.add(com);
        
        return com;
    }

    public static void enterCommunity(String name, User u)
    {
        boolean sent = false;
        for (Community c : communities) {
            if (c.name.equals(name)) {
                sent = true;
                c.requests.add(u);
                System.out.println("Seu pedido para entrar na comunidade foi enviado!");
            }
        }
        if (!sent) {
            System.out.println("Comunidade não encontrada!");
        }
    }
}
