import java.util.Scanner;
import java.util.ArrayList;

public class Feed {
    public ArrayList<Message> msgs = new ArrayList<Message>();

    public void newMessage(User sender, ArrayList<User> users)
    {
        Scanner input = new Scanner(System.in);
        Message m = Message.sendMessage(sender.nickname);
        System.out.println("[1] Publicar para amigos\n[2] Publicar para todos");
        int restricted = input.nextInt();
        for (User u : users) {
            if (restricted != 1) {
                u.feed.msgs.add(m);
            }
            else if (u.friends.contains(sender) || u == sender)
                u.feed.msgs.add(m);
        }
        System.out.println("Sua mensagem foi enviada ao feed!");
    }

    public void showFeed()
    {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        if (msgs.size() > 0)
        {
            System.out.println("Mensagens do feed:\n");
            for (Message m : msgs) {
                System.out.println(m.getDate() + " " + m.getSender() + ": " + m.getContent());
            }
        }
        else
            System.out.println("O feed está vazio!");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}